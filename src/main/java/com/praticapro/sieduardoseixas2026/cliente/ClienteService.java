package com.praticapro.sieduardoseixas2026.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository clienteRepository;

    public List<Cliente> listarTodos() {
        return clienteRepository.findAll();
    }

    public List<Cliente> listarAtivos() {
        return clienteRepository.findByAtivoTrue();
    }

    public Optional<Cliente> buscarPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Optional<Cliente> buscarPorCpfCnpj(String cpfCnpj) {
        return clienteRepository.findByCpfCnpj(limparCpfCnpj(cpfCnpj));
    }

    public List<Cliente> buscarPorNome(String nome) {
        return clienteRepository.findByNomeContainingIgnoreCase(nome);
    }

    public List<Cliente> listarPorTipo(Integer tipo) {
        return clienteRepository.findByTipo(tipo);
    }

    public Cliente salvar(Cliente cliente) {
        normalizar(cliente);
        return clienteRepository.save(cliente);
    }

    public Cliente atualizar(Long id, Cliente dados) {
        return clienteRepository.findById(id).map(cliente -> {
            cliente.setNome(dados.getNome());
            cliente.setApelido(dados.getApelido());
            cliente.setTipo(dados.getTipo());
            cliente.setCpfCnpj(dados.getCpfCnpj());
            cliente.setRgInscricaoEstadual(dados.getRgInscricaoEstadual());
            cliente.setSexo(dados.getSexo());
            cliente.setDataNascimento(dados.getDataNascimento());
            cliente.setEstadoCivil(dados.getEstadoCivil());
            cliente.setEmail(dados.getEmail());
            cliente.setTelefone(dados.getTelefone());
            cliente.setEndereco(dados.getEndereco());
            cliente.setNumero(dados.getNumero());
            cliente.setComplemento(dados.getComplemento());
            cliente.setBairro(dados.getBairro());
            cliente.setCep(dados.getCep());
            cliente.setCidade(dados.getCidade());
            cliente.setNacionalidade(dados.getNacionalidade());
            cliente.setLimiteCredito(dados.getLimiteCredito());
            cliente.setObservacao(dados.getObservacao());
            cliente.setAtivo(dados.getAtivo());
            normalizar(cliente);
            return clienteRepository.save(cliente);
        }).orElseThrow(() -> new RuntimeException("Cliente não encontrado com id: " + id));
    }

    public void deletar(Long id) {
        clienteRepository.deleteById(id);
    }

    private void normalizar(Cliente cliente) {
        if (cliente.getCpfCnpj() != null) {
            cliente.setCpfCnpj(limparCpfCnpj(cliente.getCpfCnpj()));
        }
        if (cliente.getCep() != null) {
            cliente.setCep(cliente.getCep().replaceAll("[^0-9]", ""));
        }
    }

    private String limparCpfCnpj(String valor) {
        return valor == null ? null : valor.replaceAll("[^0-9]", "");
    }
}
