package com.praticapro.sieduardoseixas2026.pais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PaisService {

    @Autowired
    private PaisRepository paisRepository;

    public List<Pais> listarTodos() {
        return paisRepository.findAll();
    }

    public Optional<Pais> buscarPorId(Long id) {
        return paisRepository.findById(id);
    }

    public Pais salvar(Pais pais) {
        return paisRepository.save(pais);
    }

    public Pais atualizar(Long id, Pais paisAtualizado) {
        return paisRepository.findById(id).map(pais -> {
            pais.setNome(paisAtualizado.getNome());
            pais.setSigla(paisAtualizado.getSigla());
            pais.setCodigo(paisAtualizado.getCodigo());
            pais.setNacionalidade(paisAtualizado.getNacionalidade());
            pais.setAtivo(paisAtualizado.getAtivo());
            return paisRepository.save(pais);
        }).orElseThrow(() -> new RuntimeException("País não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        paisRepository.deleteById(id);
    }
}
