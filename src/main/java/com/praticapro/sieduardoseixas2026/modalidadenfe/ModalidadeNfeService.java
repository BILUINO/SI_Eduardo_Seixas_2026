package com.praticapro.sieduardoseixas2026.modalidadenfe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ModalidadeNfeService {

    @Autowired
    private ModalidadeNfeRepository modalidadeNfeRepository;

    public List<ModalidadeNfe> listarTodos() {
        return modalidadeNfeRepository.findAll();
    }

    public List<ModalidadeNfe> listarAtivos() {
        return modalidadeNfeRepository.findByAtivoTrue();
    }

    public Optional<ModalidadeNfe> buscarPorId(Long id) {
        return modalidadeNfeRepository.findById(id);
    }

    public ModalidadeNfe salvar(ModalidadeNfe modalidade) {
        return modalidadeNfeRepository.save(modalidade);
    }

    public ModalidadeNfe atualizar(Long id, ModalidadeNfe modalidadeAtualizada) {
        return modalidadeNfeRepository.findById(id).map(modalidade -> {
            modalidade.setCodigo(modalidadeAtualizada.getCodigo());
            modalidade.setDescricao(modalidadeAtualizada.getDescricao());
            modalidade.setAtivo(modalidadeAtualizada.getAtivo());
            return modalidadeNfeRepository.save(modalidade);
        }).orElseThrow(() -> new RuntimeException("Modalidade NFe não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        modalidadeNfeRepository.deleteById(id);
    }
}
