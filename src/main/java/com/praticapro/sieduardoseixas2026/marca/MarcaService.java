package com.praticapro.sieduardoseixas2026.marca;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MarcaService {

    @Autowired
    private MarcaRepository marcaRepository;

    public List<Marca> listarTodos() {
        return marcaRepository.findAll();
    }

    public List<Marca> listarAtivos() {
        return marcaRepository.findByAtivoTrue();
    }

    public Optional<Marca> buscarPorId(Long id) {
        return marcaRepository.findById(id);
    }

    public Marca salvar(Marca marca) {
        return marcaRepository.save(marca);
    }

    public Marca atualizar(Long id, Marca marcaAtualizada) {
        return marcaRepository.findById(id).map(marca -> {
            marca.setNome(marcaAtualizada.getNome());
            marca.setAtivo(marcaAtualizada.getAtivo());
            return marcaRepository.save(marca);
        }).orElseThrow(() -> new RuntimeException("Marca não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        marcaRepository.deleteById(id);
    }
}
