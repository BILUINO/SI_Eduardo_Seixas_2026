package com.praticapro.sieduardoseixas2026.contapagar;

import com.praticapro.sieduardoseixas2026.condicaopagamento.CondicaoPagamento;
import com.praticapro.sieduardoseixas2026.condicaopagamento.ParcelaCondicaoPagamento;
import com.praticapro.sieduardoseixas2026.notaentrada.NotaEntrada;
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
public class ContaPagarService {

    @Autowired
    private ContaPagarRepository contaPagarRepository;

    public List<ContaPagar> listarTodos() {
        return contaPagarRepository.findAll();
    }

    public List<ContaPagar> listarPorStatus(String status) {
        return contaPagarRepository.findByStatus(status);
    }

    public List<ContaPagar> listarAbertas() {
        return contaPagarRepository.findByStatus("ABERTA");
    }

    public List<ContaPagar> listarVencidas() {
        return contaPagarRepository.findByStatusAndDataVencimentoBefore("ABERTA", LocalDate.now());
    }

    public List<ContaPagar> listarPorFornecedor(Long fornecedorId) {
        return contaPagarRepository.findByFornecedorId(fornecedorId);
    }

    public List<ContaPagar> listarPorNotaEntrada(Long notaEntradaId) {
        return contaPagarRepository.findByNotaEntradaId(notaEntradaId);
    }

    public List<ContaPagar> listarPorPeriodo(LocalDate inicio, LocalDate fim) {
        return contaPagarRepository.findByDataVencimentoBetween(inicio, fim);
    }

    public Optional<ContaPagar> buscarPorId(Long id) {
        return contaPagarRepository.findById(id);
    }

    @Transactional
    public ContaPagar salvar(ContaPagar conta) {
        validar(conta);
        if (conta.getDataEmissao() == null) conta.setDataEmissao(LocalDate.now());
        return contaPagarRepository.save(conta);
    }

    @Transactional
    public ContaPagar atualizar(Long id, ContaPagar atualizada) {
        return contaPagarRepository.findById(id).map(c -> {
            if ("PAGA".equals(c.getStatus())) {
                throw new RuntimeException("Não é possível editar uma conta já paga");
            }
            if ("CANCELADA".equals(c.getStatus())) {
                throw new RuntimeException("Não é possível editar uma conta cancelada");
            }
            validar(atualizada);
            c.setNumeroDocumento(atualizada.getNumeroDocumento());
            c.setDescricao(atualizada.getDescricao());
            c.setFornecedor(atualizada.getFornecedor());
            c.setFormaPagamento(atualizada.getFormaPagamento());
            c.setValorOriginal(atualizada.getValorOriginal());
            c.setValorJuros(atualizada.getValorJuros());
            c.setValorMulta(atualizada.getValorMulta());
            c.setValorDesconto(atualizada.getValorDesconto());
            c.setDataEmissao(atualizada.getDataEmissao());
            c.setDataVencimento(atualizada.getDataVencimento());
            c.setObservacao(atualizada.getObservacao());
            c.setUsuarioAlteracao(atualizada.getUsuarioAlteracao());
            return contaPagarRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Conta a pagar não encontrada: " + id));
    }

    @Transactional
    public ContaPagar pagar(Long id, BigDecimal valorPago, LocalDate dataPagamento,
                            BigDecimal juros, BigDecimal multa, BigDecimal desconto) {
        ContaPagar conta = contaPagarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a pagar não encontrada: " + id));

        if ("PAGA".equals(conta.getStatus())) {
            throw new RuntimeException("Conta já está paga");
        }
        if ("CANCELADA".equals(conta.getStatus())) {
            throw new RuntimeException("Não é possível pagar conta cancelada");
        }

        if (juros != null) conta.setValorJuros(juros);
        if (multa != null) conta.setValorMulta(multa);
        if (desconto != null) conta.setValorDesconto(desconto);

        BigDecimal saldoAtual = conta.getSaldo();
        BigDecimal aPagar = valorPago == null ? saldoAtual : valorPago;

        if (aPagar.compareTo(BigDecimal.ZERO) <= 0) {
            throw new RuntimeException("Valor pago deve ser maior que zero");
        }
        if (aPagar.compareTo(saldoAtual) > 0) {
            throw new RuntimeException("Valor pago (" + aPagar + ") é maior que o saldo (" + saldoAtual + ")");
        }

        BigDecimal totalPago = (conta.getValorPago() == null ? BigDecimal.ZERO : conta.getValorPago()).add(aPagar);
        conta.setValorPago(totalPago);
        conta.setDataPagamento(dataPagamento == null ? LocalDate.now() : dataPagamento);

        BigDecimal novoSaldo = conta.getValorAtualizado().subtract(totalPago);
        if (novoSaldo.compareTo(BigDecimal.ZERO) <= 0) {
            conta.setStatus("PAGA");
        } else {
            conta.setStatus("PARCIAL");
        }

        return contaPagarRepository.save(conta);
    }

    @Transactional
    public ContaPagar estornar(Long id) {
        ContaPagar conta = contaPagarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a pagar não encontrada: " + id));
        if (!"PAGA".equals(conta.getStatus()) && !"PARCIAL".equals(conta.getStatus())) {
            throw new RuntimeException("Só é possível estornar conta PAGA ou PARCIAL");
        }
        conta.setValorPago(BigDecimal.ZERO);
        conta.setDataPagamento(null);
        conta.setStatus("ABERTA");
        return contaPagarRepository.save(conta);
    }

    @Transactional
    public ContaPagar cancelar(Long id) {
        ContaPagar conta = contaPagarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a pagar não encontrada: " + id));
        if ("PAGA".equals(conta.getStatus())) {
            throw new RuntimeException("Não é possível cancelar conta já paga. Estorne primeiro.");
        }
        conta.setStatus("CANCELADA");
        return contaPagarRepository.save(conta);
    }

    @Transactional
    public void deletar(Long id) {
        ContaPagar conta = contaPagarRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Conta a pagar não encontrada: " + id));
        if ("PAGA".equals(conta.getStatus()) || "PARCIAL".equals(conta.getStatus())) {
            throw new RuntimeException("Não é possível excluir conta com pagamento. Estorne primeiro.");
        }
        contaPagarRepository.deleteById(id);
    }

    @Transactional
    public List<ContaPagar> gerarPorNotaEntrada(NotaEntrada nota) {
        if (nota.getCondicaoPagamento() == null) {
            return new ArrayList<>();
        }

        CondicaoPagamento cond = nota.getCondicaoPagamento();
        BigDecimal valorTotal = nota.getValorTotal();
        List<ContaPagar> geradas = new ArrayList<>();

        List<ParcelaCondicaoPagamento> parcelas = cond.getParcelas();
        int total = parcelas.size();

        for (ParcelaCondicaoPagamento p : parcelas) {
            ContaPagar conta = new ContaPagar();
            conta.setNotaEntrada(nota);
            conta.setFornecedor(nota.getFornecedor());
            conta.setFormaPagamento(p.getFormaPagamento());
            conta.setNumeroDocumento(nota.getNumero() + "/" + nota.getSerie());
            conta.setNumeroParcela(p.getNumeroParcela());
            conta.setTotalParcelas(total);
            conta.setDescricao("Parcela " + p.getNumeroParcela() + "/" + total
                    + " da nota " + nota.getNumero() + " - " + nota.getFornecedor().getFornecedor());

            BigDecimal valorParcela = valorTotal
                    .multiply(p.getPercentual())
                    .divide(new BigDecimal("100"), 2, RoundingMode.HALF_UP);
            conta.setValorOriginal(valorParcela);

            conta.setDataEmissao(nota.getDataEntrada());
            conta.setDataVencimento(nota.getDataEntrada().plusDays(p.getDias()));
            conta.setStatus("ABERTA");
            conta.setUsuarioCadastro("SISTEMA");

            geradas.add(contaPagarRepository.save(conta));
        }

        return geradas;
    }

    private void validar(ContaPagar c) {
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
