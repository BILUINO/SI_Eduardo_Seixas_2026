package com.praticapro.sieduardoseixas2026.cidade;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> listarTodos() {
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> buscarPorId(Long id) {
        return cidadeRepository.findById(id);
    }

    public Cidade salvar(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public Cidade atualizar(Long id, Cidade cidadeAtualizada) {
        return cidadeRepository.findById(id).map(cidade -> {
            cidade.setNome(cidadeAtualizada.getNome());
            cidade.setCodigoIbge(cidadeAtualizada.getCodigoIbge());
            cidade.setEstado(cidadeAtualizada.getEstado());
            cidade.setAtivo(cidadeAtualizada.getAtivo());
            return cidadeRepository.save(cidade);
        }).orElseThrow(() -> new RuntimeException("Cidade não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        cidadeRepository.deleteById(id);
    }
}
