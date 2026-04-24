package com.praticapro.sieduardoseixas2026.contareceber;

import com.praticapro.sieduardoseixas2026.condicaopagamento.CondicaoPagamento;
import com.praticapro.sieduardoseixas2026.condicaopagamento.ParcelaCondicaoPagamento;
import com.praticapro.sieduardoseixas2026.notasaida.NotaSaida;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ContaReceberService {

    @Autowired
    private ContaReceberRepository contaReceberRepository;

    public List<ContaReceber> listarTodos() {
        return contaReceberRepository.findAll();
    }

    public List<ContaReceber> listarPorStatus(String status) {
        return contaReceberRepository.findByStatus(status);
    }

    public List<ContaReceber> listarAbertas() {
        return contaReceberRepository.findByStatus("ABERTA");
    }

    public List<ContaReceber> listarVencidas() {
        return contaReceberRepository.findByStatusAndDataVencimentoBefore("ABERTA", LocalDate.now());
    }

    public List<ContaReceber> listarPorCliente(Long clienteId) {
        return contaReceberRepository.findByClienteId(clienteId);
    }

    public List<ContaReceber> listarPorNotaSaida(Long notaSaidaId) {
        return contaReceberRepository.findByNotaSaidaId(notaSaidaId);
    }

    public List<ContaReceber> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return contaReceberRepository.findByDataVencimentoBetween(inicio, fim);
    }

    public Optional<ContaReceber> buscarPorId(Long id) {
        return contaReceberRepository.findById(id);
    }

    @Transactional
    public ContaReceber salvar(ContaReceber conta) {
        validar(conta);
        if (conta.getDataEmissao() == null) conta.setDataEmissao(LocalDate.now());
        return contaReceberRepository.save(conta);
    }

    @Transactional
    public ContaReceber atualizar(Long id, ContaReceber atualizada) {
        return contaReceberRepository.findById(id).map(c -> {
            if ("RECEBIDA".equals(c.getStatus())) {
                throw new RuntimeException("Não é possível editar uma conta já recebida");
            }
            if ("CANCELADA".equals(c.getStatus())) {
                throw new RuntimeException("Não é possível editar uma conta cancelada");
            }
            validar(atualizada);
            c.setNumeroDocumento(atualizada.getNumeroDocumento());
            c.setDescricao(atualizada.getDescricao());
            c.setCliente(atualizada.getCliente());
            c.setFormaPagamento(atualizada.getFormaPagamento());
            c.setValorOriginal(atualizada.getValorOriginal());
            c.setValorJuros(atualizada.getValorJuros());
            c.setValorMulta(atualizada.getValorMulta());
            c.setValorDesconto(atualizada.getValorDesconto());
            c.setDataEmissao(atualizada.getDataEmissao());
            c.setDataVencimento(atualizada.getDataVencimento());
            c.setObservacao(atualizada.getObservacao());
            c.setUsuarioAlteracao(atualizada.getUsuarioAlteracao());
            return contaReceberRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Conta a receber não encontrada: " + id));
    }

    @Transactional
    public ContaReceber receber(Long id, BigDecimal valorRecebido, LocalDate dataRecebimento,
                                BigDecimal juros, BigDecimal multa, BigDecimal desconto) {
        ContaReceber conta = contaReceberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a receber não encontrada: " + id));

        if ("RECEBIDA".equals(conta.getStatus())) {
            throw new RuntimeException("Conta já está recebida");
        }
        if ("CANCELADA".equals(conta.getStatus())) {
            throw new RuntimeException("Não é possível receber conta cancelada");
        }

        if (juros != null) conta.setValorJuros(juros);
        if (multa != null) conta.setValorMulta(multa);
        if (desconto != null) conta.setValorDesconto(desconto);

        BigDecimal saldoAtual = conta.getSaldo();
        BigDecimal aReceber = valorRecebido == null ? saldoAtual : valorRecebido;

        if (aReceber.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor recebido deve ser maior que zero");
        }
        if (aReceber.compareTo(saldoAtual) > 0) {
            throw new RuntimeException("Valor recebido (" + aReceber + ") é maior que o saldo (" + saldoAtual + ")");
        }

        BigDecimal totalRecebido = (conta.getValorRecebido() == null ? BigDecimal.ZERO : conta.getValorRecebido()).add(aReceber);
        conta.setValorRecebido(totalRecebido);
        conta.setDataRecebimento(dataRecebimento == null ? LocalDate.now() : dataRecebimento);

        BigDecimal novoSaldo = conta.getValorAtualizado().subtract(totalRecebido);
        if (novoSaldo.compareTo(BigDecimal.ZERO) <= 0) {
            conta.setStatus("RECEBIDA");
        } else {
            conta.setStatus("PARCIAL");
        }

        return contaReceberRepository.save(conta);
    }

    @Transactional
    public ContaReceber estornar(Long id) {
        ContaReceber conta = contaReceberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a receber não encontrada: " + id));
        if (!"RECEBIDA".equals(conta.getStatus()) && !"PARCIAL".equals(conta.getStatus())) {
            throw new RuntimeException("Só é possível estornar conta RECEBIDA ou PARCIAL");
        }
        conta.setValorRecebido(BigDecimal.ZERO);
        conta.setDataRecebimento(null);
        conta.setStatus("ABERTA");
        return contaReceberRepository.save(conta);
    }

    @Transactional
    public ContaReceber cancelar(Long id) {
        ContaReceber conta = contaReceberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a receber não encontrada: " + id));
        if ("RECEBIDA".equals(conta.getStatus())) {
            throw new RuntimeException("Não é possível cancelar conta já recebida. Estorne primeiro.");
        }
        conta.setStatus("CANCELADA");
        return contaReceberRepository.save(conta);
    }

    @Transactional
    public void deletar(Long id) {
        ContaReceber conta = contaReceberRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a receber não encontrada: " + id));
        if ("RECEBIDA".equals(conta.getStatus()) || "PARCIAL".equals(conta.getStatus())) {
            throw new RuntimeException("Não é possível excluir conta com recebimento. Estorne primeiro.");
        }
        contaReceberRepository.deleteById(id);
    }

    @Transactional
    public List<ContaReceber> gerarPorNotaSaida(NotaSaida nota) {
        if (nota.getCondicaoPagamento() == null) {
            return new ArrayList<>();
        }

        CondicaoPagamento cond = nota.getCondicaoPagamento();
        BigDecimal valorTotal = nota.getValorTotal();
        List<ContaReceber> geradas = new ArrayList<>();

        List<ParcelaCondicaoPagamento> parcelas = cond.getParcelas();
        int total = parcelas.size();

        for (ParcelaCondicaoPagamento p : parcelas) {
            ContaReceber conta = new ContaReceber();
            conta.setNotaSaida(nota);
            conta.setCliente(nota.getCliente());
            conta.setFormaPagamento(p.getFormaPagamento());
            conta.setNumeroDocumento(nota.getNumero() + "/" + nota.getSerie());
            conta.setNumeroParcela(p.getNumeroParcela());
            conta.setTotalParcelas(total);
            conta.setDescricao("Parcela " + p.getNumeroParcela() + "/" + total
                    + " da nota " + nota.getNumero() + " - " + nota.getCliente().getNome());

            BigDecimal valorParcela = valorTotal
                    .multiply(p.getPercentual())
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            conta.setValorOriginal(valorParcela);

            conta.setDataEmissao(nota.getDataSaida());
            conta.setDataVencimento(nota.getDataSaida().plusDays(p.getDias()));
            conta.setStatus("ABERTA");
            conta.setUsuarioCadastro("SISTEMA");

            geradas.add(contaReceberRepository.save(conta));
        }

        return geradas;
    }

    private void validar(ContaReceber c) {
        if (c.getValorOriginal() == null || c.getValorOriginal().compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor original deve ser maior que zero");
        }
        if (c.getDataVencimento() == null) {
            throw new RuntimeException("Data de vencimento é obrigatória");
        }
        if (c.getDataEmissao() != null && c.getDataVencimento().isBefore(c.getDataEmissao())) {
            throw new RuntimeException("Vencimento não pode ser anterior à emissão");
        }
    }
}
