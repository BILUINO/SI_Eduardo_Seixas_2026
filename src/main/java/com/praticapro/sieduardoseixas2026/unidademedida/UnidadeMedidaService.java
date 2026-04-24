package com.praticapro.sieduardoseixas2026.unidademedida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UnidadeMedidaService {

    @Autowired
    private UnidadeMedidaRepository unidadeMedidaRepository;

    public List<UnidadeMedida> listarTodos() {
        return unidadeMedidaRepository.findAll();
    }

    public List<UnidadeMedida> listarAtivos() {
        return unidadeMedidaRepository.findByAtivoTrue();
    }

    public Optional<UnidadeMedida> buscarPorId(Long id) {
        return unidadeMedidaRepository.findById(id);
    }

    public UnidadeMedida salvar(UnidadeMedida unidadeMedida) {
        return unidadeMedidaRepository.save(unidadeMedida);
    }

    public UnidadeMedida atualizar(Long id, UnidadeMedida unidadeAtualizada) {
        return unidadeMedidaRepository.findById(id).map(unidade -> {
            unidade.setNome(unidadeAtualizada.getNome());
            unidade.setSigla(unidadeAtualizada.getSigla());
            unidade.setAtivo(unidadeAtualizada.getAtivo());
            return unidadeMedidaRepository.save(unidade);
        }).orElseThrow(() -> new RuntimeException("Unidade de medida não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        unidadeMedidaRepository.deleteById(id);
    }
}
