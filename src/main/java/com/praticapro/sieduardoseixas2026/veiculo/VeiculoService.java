package com.praticapro.sieduardoseixas2026.veiculo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VeiculoService {

    @Autowired
    private VeiculoRepository veiculoRepository;

    public List<Veiculo> listarTodos() {
        return veiculoRepository.findAll();
    }

    public List<Veiculo> listarAtivos() {
        return veiculoRepository.findByAtivoTrue();
    }

    public Optional<Veiculo> buscarPorId(Long id) {
        return veiculoRepository.findById(id);
    }

    public Optional<Veiculo> buscarPorPlaca(String placa) {
        return veiculoRepository.findByPlaca(placa);
    }

    public Veiculo salvar(Veiculo veiculo) {
        return veiculoRepository.save(veiculo);
    }

    public Veiculo atualizar(Long id, Veiculo veiculoAtualizado) {
        return veiculoRepository.findById(id).map(veiculo -> {
            veiculo.setPlaca(veiculoAtualizado.getPlaca());
            veiculo.setModelo(veiculoAtualizado.getModelo());
            veiculo.setMarca(veiculoAtualizado.getMarca());
            veiculo.setAno(veiculoAtualizado.getAno());
            veiculo.setCapacidade(veiculoAtualizado.getCapacidade());
            veiculo.setAtivo(veiculoAtualizado.getAtivo());
            return veiculoRepository.save(veiculo);
        }).orElseThrow(() -> new RuntimeException("Veículo não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        veiculoRepository.deleteById(id);
    }
}
