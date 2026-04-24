package com.praticapro.sieduardoseixas2026.notasaida;

import com.praticapro.sieduardoseixas2026.contareceber.ContaReceberService;
import com.praticapro.sieduardoseixas2026.produto.Produto;
import com.praticapro.sieduardoseixas2026.produto.ProdutoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class NotaSaidaService {

    @Autowired
    private NotaSaidaRepository notaSaidaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ContaReceberService contaReceberService;

    public List<NotaSaida> listarTodos() {
        return notaSaidaRepository.findAll();
    }

    public List<NotaSaida> listarPorStatus(String status) {
        return notaSaidaRepository.findByStatus(status);
    }

    public List<NotaSaida> listarPorCliente(Long clienteId) {
        return notaSaidaRepository.findByClienteId(clienteId);
    }

    public List<NotaSaida> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return notaSaidaRepository.findByDataSaidaBetween(inicio, fim);
    }

    public Optional<NotaSaida> buscarPorId(Long id) {
        return notaSaidaRepository.findById(id);
    }

    @Transactional
    public NotaSaida salvar(NotaSaida nota) {
        validar(nota);
        if (notaSaidaRepository.existsByNumeroAndSerie(nota.getNumero(), nota.getSerie())) {
            throw new RuntimeException("Já existe nota de saída com mesmo número e série");
        }
        vincularItens(nota);
        nota.setStatus("PENDENTE");
        return notaSaidaRepository.save(nota);
    }

    @Transactional
    public NotaSaida atualizar(Long id, NotaSaida atualizada) {
        return notaSaidaRepository.findById(id).map(n -> {
            if (!"PENDENTE".equals(n.getStatus())) {
                throw new RuntimeException("Só é possível editar nota com status PENDENTE");
            }
            validar(atualizada);

            n.setNumero(atualizada.getNumero());
            n.setSerie(atualizada.getSerie());
            n.setChaveAcesso(atualizada.getChaveAcesso());
            n.setModalidadeNfe(atualizada.getModalidadeNfe());
            n.setDataEmissao(atualizada.getDataEmissao());
            n.setDataSaida(atualizada.getDataSaida());
            n.setCliente(atualizada.getCliente());
            n.setTransportadora(atualizada.getTransportadora());
            n.setCondicaoPagamento(atualizada.getCondicaoPagamento());
            n.setValorFrete(atualizada.getValorFrete());
            n.setValorSeguro(atualizada.getValorSeguro());
            n.setValorOutrasDespesas(atualizada.getValorOutrasDespesas());
            n.setValorDesconto(atualizada.getValorDesconto());
            n.setValorIcms(atualizada.getValorIcms());
            n.setValorIpi(atualizada.getValorIpi());
            n.setObservacao(atualizada.getObservacao());
            n.setUsuarioAlteracao(atualizada.getUsuarioAlteracao());

            n.getItens().clear();
            if (atualizada.getItens() != null) {
                atualizada.getItens().forEach(item -> {
                    item.setNotaSaida(n);
                    n.getItens().add(item);
                });
            }

            return notaSaidaRepository.save(n);
        }).orElseThrow(() -> new RuntimeException("Nota de saída não encontrada: " + id));
    }

    @Transactional
    public NotaSaida confirmar(Long id) {
        NotaSaida nota = notaSaidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota de saída não encontrada: " + id));

        if (!"PENDENTE".equals(nota.getStatus())) {
            throw new RuntimeException("Só é possível confirmar nota com status PENDENTE. Status atual: " + nota.getStatus());
        }
        if (nota.getItens() == null || nota.getItens().isEmpty()) {
            throw new RuntimeException("Não é possível confirmar nota sem itens");
        }

        for (ItemNotaSaida item : nota.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProduto().getId()));

            BigDecimal estoqueAtual = produto.getQuantidade() == null ? BigDecimal.ZERO : produto.getQuantidade();
            if (estoqueAtual.compareTo(item.getQuantidade()) < 0) {
                throw new RuntimeException("Estoque insuficiente para o produto '" + produto.getNome()
                        + "'. Disponível: " + estoqueAtual + ", solicitado: " + item.getQuantidade());
            }
        }

        for (ItemNotaSaida item : nota.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId()).get();

            BigDecimal estoqueAtual = produto.getQuantidade();
            produto.setQuantidade(estoqueAtual.subtract(item.getQuantidade()));

            produto.setValorUltimaVenda(item.getValorUnitario());
            produto.setDataUltimaVenda(LocalDate.now());

            produtoRepository.save(produto);
        }

        nota.setStatus("CONFIRMADA");
        nota.setDataConfirmacao(LocalDateTime.now());

        if (nota.getCondicaoPagamento() != null) {
            contaReceberService.gerarPorNotaSaida(nota);
        }

        return notaSaidaRepository.save(nota);
    }

    @Transactional
    public NotaSaida cancelar(Long id) {
        NotaSaida nota = notaSaidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota de saída não encontrada: " + id));

        if ("CANCELADA".equals(nota.getStatus())) {
            throw new RuntimeException("Nota já está cancelada");
        }

        if ("CONFIRMADA".equals(nota.getStatus())) {
            for (ItemNotaSaida item : nota.getItens()) {
                Produto produto = produtoRepository.findById(item.getProduto().getId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProduto().getId()));

                BigDecimal estoqueAtual = produto.getQuantidade() == null ? BigDecimal.ZERO : produto.getQuantidade();
                produto.setQuantidade(estoqueAtual.add(item.getQuantidade()));
                produtoRepository.save(produto);
            }
        }

        nota.setStatus("CANCELADA");
        nota.setDataCancelamento(LocalDateTime.now());
        return notaSaidaRepository.save(nota);
    }

    @Transactional
    public void deletar(Long id) {
        NotaSaida nota = notaSaidaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota de saída não encontrada: " + id));

        if ("CONFIRMADA".equals(nota.getStatus())) {
            throw new RuntimeException("Não é possível excluir nota CONFIRMADA. Cancele primeiro.");
        }

        notaSaidaRepository.deleteById(id);
    }

    private void validar(NotaSaida n) {
        if (n.getNumero() == null || n.getNumero().trim().isEmpty()) {
            throw new RuntimeException("Número da nota é obrigatório");
        }
        if (n.getSerie() == null || n.getSerie().trim().isEmpty()) {
            throw new RuntimeException("Série da nota é obrigatória");
        }
        if (n.getDataEmissao() == null) {
            throw new RuntimeException("Data de emissão é obrigatória");
        }
        if (n.getDataSaida() == null) {
            throw new RuntimeException("Data de saída é obrigatória");
        }
        if (n.getDataSaida().isBefore(n.getDataEmissao())) {
            throw new RuntimeException("Data de saída não pode ser anterior à data de emissão");
        }
        if (n.getCliente() == null || n.getCliente().getId() == null) {
            throw new RuntimeException("Cliente é obrigatório");
        }
        if (n.getItens() == null || n.getItens().isEmpty()) {
            throw new RuntimeException("A nota deve ter ao menos um item");
        }
        for (ItemNotaSaida item : n.getItens()) {
            if (item.getProduto() == null || item.getProduto().getId() == null) {
                throw new RuntimeException("Produto é obrigatório em todos os itens");
            }
            if (item.getQuantidade() == null || item.getQuantidade().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Quantidade deve ser maior que zero");
            }
            if (item.getValorUnitario() == null || item.getValorUnitario().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Valor unitário deve ser maior que zero");
            }
        }
    }

    private void vincularItens(NotaSaida n) {
        if (n.getItens() != null) {
            for (ItemNotaSaida item : n.getItens()) {
                item.setNotaSaida(n);
            }
        }
    }
}
