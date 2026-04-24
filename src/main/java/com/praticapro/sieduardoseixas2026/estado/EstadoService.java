package com.praticapro.sieduardoseixas2026.estado;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstadoService {

    @Autowired
    private EstadoRepository estadoRepository;

    public List<Estado> listarTodos() {
        return estadoRepository.findAll();
    }

    public Optional<Estado> buscarPorId(Long id) {
        return estadoRepository.findById(id);
    }

    public Estado salvar(Estado estado) {
        return estadoRepository.save(estado);
    }

    public Estado atualizar(Long id, Estado estadoAtualizado) {
        return estadoRepository.findById(id).map(estado -> {
            estado.setNome(estadoAtualizado.getNome());
            estado.setUf(estadoAtualizado.getUf());
            estado.setPais(estadoAtualizado.getPais());
            estado.setAtivo(estadoAtualizado.getAtivo());
            return estadoRepository.save(estado);
        }).orElseThrow(() -> new RuntimeException("Estado não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        estadoRepository.deleteById(id);
    }
}
