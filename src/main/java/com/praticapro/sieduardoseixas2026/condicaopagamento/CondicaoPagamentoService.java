package com.praticapro.sieduardoseixas2026.condicaopagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
public class CondicaoPagamentoService {

    @Autowired
    private CondicaoPagamentoRepository condicaoPagamentoRepository;

    public List<CondicaoPagamento> listarTodos() {
        return condicaoPagamentoRepository.findAll();
    }

    public List<CondicaoPagamento> listarAtivos() {
        return condicaoPagamentoRepository.findByAtivoTrue();
    }

    public List<CondicaoPagamento> listarAVista() {
        return condicaoPagamentoRepository.findByAVistaTrue();
    }

    public Optional<CondicaoPagamento> buscarPorId(Long id) {
        return condicaoPagamentoRepository.findById(id);
    }

    public List<CondicaoPagamento> buscarPorNome(String nome) {
        return condicaoPagamentoRepository.findByCondicaoPagamentoContainingIgnoreCase(nome);
    }

    public CondicaoPagamento salvar(CondicaoPagamento condicao) {
        validar(condicao);
        vincularParcelas(condicao);
        return condicaoPagamentoRepository.save(condicao);
    }

    public CondicaoPagamento atualizar(Long id, CondicaoPagamento atualizado) {
        return condicaoPagamentoRepository.findById(id).map(c -> {
            validar(atualizado);
            c.setCondicaoPagamento(atualizado.getCondicaoPagamento());
            c.setDescricao(atualizado.getDescricao());
            c.setNumeroParcelas(atualizado.getNumeroParcelas());
            c.setAVista(atualizado.getAVista());
            c.setPercentualJuros(atualizado.getPercentualJuros());
            c.setPercentualMulta(atualizado.getPercentualMulta());
            c.setPercentualDesconto(atualizado.getPercentualDesconto());
            c.setAtivo(atualizado.getAtivo());
            c.setUsuarioAlteracao(atualizado.getUsuarioAlteracao());

            c.getParcelas().clear();
            if (atualizado.getParcelas() != null) {
                atualizado.getParcelas().forEach(p -> {
                    p.setCondicaoPagamento(c);
                    c.getParcelas().add(p);
                });
            }

            return condicaoPagamentoRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Condição de Pagamento não encontrada: " + id));
    }

    public void deletar(Long id) {
        if (!condicaoPagamentoRepository.existsById(id)) {
            throw new RuntimeException("Condição de Pagamento não encontrada: " + id);
        }
        condicaoPagamentoRepository.deleteById(id);
    }

    public CondicaoPagamento inativar(Long id) {
        return condicaoPagamentoRepository.findById(id).map(c -> {
            c.setAtivo(false);
            return condicaoPagamentoRepository.save(c);
        }).orElseThrow(() -> new RuntimeException("Condição de Pagamento não encontrada: " + id));
    }

    private void validar(CondicaoPagamento c) {
        if (c.getCondicaoPagamento() == null || c.getCondicaoPagamento().trim().isEmpty()) {
            throw new RuntimeException("Nome da condição de pagamento é obrigatório");
        }
        if (c.getNumeroParcelas() == null || c.getNumeroParcelas() < 1) {
            throw new RuntimeException("Número de parcelas deve ser maior que zero");
        }
        if (Boolean.TRUE.equals(c.getAVista()) && c.getNumeroParcelas() != 1) {
            throw new RuntimeException("Condição à vista deve ter apenas 1 parcela");
        }
        if (c.getParcelas() == null || c.getParcelas().isEmpty()) {
            throw new RuntimeException("É necessário informar ao menos uma parcela");
        }
        if (c.getParcelas().size() != c.getNumeroParcelas()) {
            throw new RuntimeException("Quantidade de parcelas (" + c.getParcelas().size()
                    + ") diferente do número informado (" + c.getNumeroParcelas() + ")");
        }

        BigDecimal somaPercentuais = c.getParcelas().stream()
                .map(ParcelaCondicaoPagamento::getPercentual)
                .filter(p -> p != null)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        if (somaPercentuais.compareTo(new BigDecimal("100.00")) != 0) {
            throw new RuntimeException("A soma dos percentuais das parcelas deve ser 100%. Atual: " + somaPercentuais + "%");
        }

        for (ParcelaCondicaoPagamento p : c.getParcelas()) {
            if (p.getNumeroParcela() == null || p.getNumeroParcela() < 1) {
                throw new RuntimeException("Número da parcela inválido");
            }
            if (p.getDias() == null || p.getDias() < 0) {
                throw new RuntimeException("Dias da parcela " + p.getNumeroParcela() + " inválido");
            }
            if (p.getPercentual() == null || p.getPercentual().compareTo(BigDecimal.ZERO) <= 0) {
                throw new RuntimeException("Percentual da parcela " + p.getNumeroParcela() + " deve ser maior que zero");
            }
            if (p.getFormaPagamento() == null || p.getFormaPagamento().getId() == null) {
                throw new RuntimeException("Forma de pagamento é obrigatória na parcela " + p.getNumeroParcela());
            }
        }
    }

    private void vincularParcelas(CondicaoPagamento c) {
        if (c.getParcelas() != null) {
            for (ParcelaCondicaoPagamento p : c.getParcelas()) {
                p.setCondicaoPagamento(c);
            }
        }
    }
}
