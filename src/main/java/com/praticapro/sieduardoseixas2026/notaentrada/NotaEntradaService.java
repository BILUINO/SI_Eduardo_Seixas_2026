package com.praticapro.sieduardoseixas2026.notaentrada;

import com.praticapro.sieduardoseixas2026.contapagar.ContaPagarService;
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
public class NotaEntradaService {

    @Autowired
    private NotaEntradaRepository notaEntradaRepository;

    @Autowired
    private ProdutoRepository produtoRepository;

    @Autowired
    private ContaPagarService contaPagarService;

    public List<NotaEntrada> listarTodos() {
        return notaEntradaRepository.findAll();
    }

    public List<NotaEntrada> listarPorStatus(String status) {
        return notaEntradaRepository.findByStatus(status);
    }

    public List<NotaEntrada> listarPorFornecedor(Long fornecedorId) {
        return notaEntradaRepository.findByFornecedorId(fornecedorId);
    }

    public List<NotaEntrada> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return notaEntradaRepository.findByDataEntradaBetween(inicio, fim);
    }

    public Optional<NotaEntrada> buscarPorId(Long id) {
        return notaEntradaRepository.findById(id);
    }

    @Transactional
    public NotaEntrada salvar(NotaEntrada nota) {
        validar(nota);
        if (nota.getFornecedor() != null && nota.getFornecedor().getId() != null
                && notaEntradaRepository.existsByNumeroAndSerieAndFornecedorId(
                nota.getNumero(), nota.getSerie(), nota.getFornecedor().getId())) {
            throw new RuntimeException("Já existe nota com mesmo número, série e fornecedor");
        }
        vincularItens(nota);
        nota.setStatus("PENDENTE");
        return notaEntradaRepository.save(nota);
    }

    @Transactional
    public NotaEntrada atualizar(Long id, NotaEntrada atualizada) {
        return notaEntradaRepository.findById(id).map(n -> {
            if (!"PENDENTE".equals(n.getStatus())) {
                throw new RuntimeException("Só é possível editar nota com status PENDENTE");
            }
            validar(atualizada);

            n.setNumero(atualizada.getNumero());
            n.setSerie(atualizada.getSerie());
            n.setChaveAcesso(atualizada.getChaveAcesso());
            n.setModalidadeNfe(atualizada.getModalidadeNfe());
            n.setDataEmissao(atualizada.getDataEmissao());
            n.setDataEntrada(atualizada.getDataEntrada());
            n.setFornecedor(atualizada.getFornecedor());
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
                    item.setNotaEntrada(n);
                    n.getItens().add(item);
                });
            }

            return notaEntradaRepository.save(n);
        }).orElseThrow(() -> new RuntimeException("Nota de entrada não encontrada: " + id));
    }

    @Transactional
    public NotaEntrada confirmar(Long id) {
        NotaEntrada nota = notaEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota de entrada não encontrada: " + id));

        if (!"PENDENTE".equals(nota.getStatus())) {
            throw new RuntimeException("Só é possível confirmar nota com status PENDENTE. Status atual: " + nota.getStatus());
        }
        if (nota.getItens() == null || nota.getItens().isEmpty()) {
            throw new RuntimeException("Não é possível confirmar nota sem itens");
        }

        for (ItemNotaEntrada item : nota.getItens()) {
            Produto produto = produtoRepository.findById(item.getProduto().getId())
                    .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProduto().getId()));

            BigDecimal estoqueAtual = produto.getQuantidade() == null ? BigDecimal.ZERO : produto.getQuantidade();
            produto.setQuantidade(estoqueAtual.add(item.getQuantidade()));

            produto.setValorCompra(item.getValorUnitario());
            produto.setValorUltimaCompra(item.getValorUnitario());
            produto.setDataUltimaCompra(LocalDate.now());

            produtoRepository.save(produto);
        }

        nota.setStatus("CONFIRMADA");
        nota.setDataConfirmacao(LocalDateTime.now());

        if (nota.getCondicaoPagamento() != null) {
            contaPagarService.gerarPorNotaEntrada(nota);
        }

        return notaEntradaRepository.save(nota);
    }

    @Transactional
    public NotaEntrada cancelar(Long id) {
        NotaEntrada nota = notaEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota de entrada não encontrada: " + id));

        if ("CANCELADA".equals(nota.getStatus())) {
            throw new RuntimeException("Nota já está cancelada");
        }

        if ("CONFIRMADA".equals(nota.getStatus())) {
            for (ItemNotaEntrada item : nota.getItens()) {
                Produto produto = produtoRepository.findById(item.getProduto().getId())
                        .orElseThrow(() -> new RuntimeException("Produto não encontrado: " + item.getProduto().getId()));

                BigDecimal estoqueAtual = produto.getQuantidade() == null ? BigDecimal.ZERO : produto.getQuantidade();
                BigDecimal novoEstoque = estoqueAtual.subtract(item.getQuantidade());

                if (novoEstoque.compareTo(BigDecimal.ZERO) < 0) {
                    throw new RuntimeException("Não é possível cancelar: o produto " + produto.getNome()
                            + " já teve sua saída e ficaria com estoque negativo");
                }

                produto.setQuantidade(novoEstoque);
                produtoRepository.save(produto);
            }
        }

        nota.setStatus("CANCELADA");
        nota.setDataCancelamento(LocalDateTime.now());
        return notaEntradaRepository.save(nota);
    }

    @Transactional
    public void deletar(Long id) {
        NotaEntrada nota = notaEntradaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Nota de entrada não encontrada: " + id));

        if ("CONFIRMADA".equals(nota.getStatus())) {
            throw new RuntimeException("Não é possível excluir nota CONFIRMADA. Cancele primeiro.");
        }

        notaEntradaRepository.deleteById(id);
    }

    private void validar(NotaEntrada n) {
        if (n.getNumero() == null || n.getNumero().trim().isEmpty()) {
            throw new RuntimeException("Número da nota é obrigatório");
        }
        if (n.getSerie() == null || n.getSerie().trim().isEmpty()) {
            throw new RuntimeException("Série da nota é obrigatória");
        }
        if (n.getDataEmissao() == null) {
            throw new RuntimeException("Data de emissão é obrigatória");
        }
        if (n.getDataEntrada() == null) {
            throw new RuntimeException("Data de entrada é obrigatória");
        }
        if (n.getDataEntrada().isBefore(n.getDataEmissao())) {
            throw new RuntimeException("Data de entrada não pode ser anterior à data de emissão");
        }
        if (n.getFornecedor() == null || n.getFornecedor().getId() == null) {
            throw new RuntimeException("Fornecedor é obrigatório");
        }
        if (n.getItens() == null || n.getItens().isEmpty()) {
            throw new RuntimeException("A nota deve ter ao menos um item");
        }
        for (ItemNotaEntrada item : n.getItens()) {
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

    private void vincularItens(NotaEntrada n) {
        if (n.getItens() != null) {
            for (ItemNotaEntrada item : n.getItens()) {
                item.setNotaEntrada(n);
            }
        }
    }
}
