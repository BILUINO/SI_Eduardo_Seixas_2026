package com.praticapro.sieduardoseixas2026.formapagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FormaPagamentoService {

    @Autowired
    private FormaPagamentoRepository formaPagamentoRepository;

    public List<FormaPagamento> listarTodos() {
        return formaPagamentoRepository.findAll();
    }

    public List<FormaPagamento> listarAtivos() {
        return formaPagamentoRepository.findByAtivoTrue();
    }

    public Optional<FormaPagamento> buscarPorId(Long id) {
        return formaPagamentoRepository.findById(id);
    }

    public FormaPagamento salvar(FormaPagamento formaPagamento) {
        return formaPagamentoRepository.save(formaPagamento);
    }

    public FormaPagamento atualizar(Long id, FormaPagamento formaPagamentoAtualizada) {
        return formaPagamentoRepository.findById(id).map(forma -> {
            forma.setNome(formaPagamentoAtualizada.getNome());
            forma.setDescricao(formaPagamentoAtualizada.getDescricao());
            forma.setAtivo(formaPagamentoAtualizada.getAtivo());
            return formaPagamentoRepository.save(forma);
        }).orElseThrow(() -> new RuntimeException("Forma de pagamento não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        formaPagamentoRepository.deleteById(id);
    }
}
