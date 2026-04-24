package com.praticapro.sieduardoseixas2026.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncaoFuncionarioService {

    @Autowired
    private FuncaoFuncionarioRepository funcaoFuncionarioRepository;

    public List<FuncaoFuncionario> listarTodos() {
        return funcaoFuncionarioRepository.findAll();
    }

    public List<FuncaoFuncionario> listarAtivos() {
        return funcaoFuncionarioRepository.findByAtivoTrue();
    }

    public Optional<FuncaoFuncionario> buscarPorId(Long id) {
        return funcaoFuncionarioRepository.findById(id);
    }

    public FuncaoFuncionario salvar(FuncaoFuncionario funcao) {
        return funcaoFuncionarioRepository.save(funcao);
    }

    public FuncaoFuncionario atualizar(Long id, FuncaoFuncionario funcaoAtualizada) {
        return funcaoFuncionarioRepository.findById(id).map(funcao -> {
            funcao.setNome(funcaoAtualizada.getNome());
            funcao.setDescricao(funcaoAtualizada.getDescricao());
            funcao.setSalarioBase(funcaoAtualizada.getSalarioBase());
            funcao.setCargaHoraria(funcaoAtualizada.getCargaHoraria());
            funcao.setRequerCnh(funcaoAtualizada.getRequerCnh());
            funcao.setObservacao(funcaoAtualizada.getObservacao());
            funcao.setAtivo(funcaoAtualizada.getAtivo());
            return funcaoFuncionarioRepository.save(funcao);
        }).orElseThrow(() -> new RuntimeException("Função não encontrada com id: " + id));
    }

    public void deletar(Long id) {
        funcaoFuncionarioRepository.deleteById(id);
    }
}
