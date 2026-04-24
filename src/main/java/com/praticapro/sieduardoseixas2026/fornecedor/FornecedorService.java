package com.praticapro.sieduardoseixas2026.fornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FornecedorService {

    @Autowired
    private FornecedorRepository fornecedorRepository;

    public List<Fornecedor> listarTodos() {
        return fornecedorRepository.findAll();
    }

    public List<Fornecedor> listarAtivos() {
        return fornecedorRepository.findByAtivoTrue();
    }

    public Optional<Fornecedor> buscarPorId(Long id) {
        return fornecedorRepository.findById(id);
    }

    public Optional<Fornecedor> buscarPorCpfCnpj(String cpfCnpj) {
        String limpo = cpfCnpj == null ? null : cpfCnpj.replaceAll("[^0-9]", "");
        return fornecedorRepository.findByCpfCnpj(limpo);
    }

    public List<Fornecedor> buscarPorNome(String nome) {
        return fornecedorRepository.findByFornecedorContainingIgnoreCase(nome);
    }

    public List<Fornecedor> buscarPorTipo(Integer tipo) {
        return fornecedorRepository.findByTipo(tipo);
    }

    public Fornecedor salvar(Fornecedor fornecedor) {
        validar(fornecedor);
        if (fornecedor.getCpfCnpj() != null) {
            String limpo = fornecedor.getCpfCnpj().replaceAll("[^0-9]", "");
            if (fornecedorRepository.existsByCpfCnpj(limpo)) {
                throw new RuntimeException("Já existe um fornecedor cadastrado com este CPF/CNPJ");
            }
        }
        vincularFilhos(fornecedor);
        return fornecedorRepository.save(fornecedor);
    }

    public Fornecedor atualizar(Long id, Fornecedor fornecedorAtualizado) {
        return fornecedorRepository.findById(id).map(f -> {
            validar(fornecedorAtualizado);
            f.setFornecedor(fornecedorAtualizado.getFornecedor());
            f.setApelido(fornecedorAtualizado.getApelido());
            f.setTipo(fornecedorAtualizado.getTipo());
            f.setCpfCnpj(fornecedorAtualizado.getCpfCnpj());
            f.setRgIe(fornecedorAtualizado.getRgIe());
            f.setDataNascimentoAbertura(fornecedorAtualizado.getDataNascimentoAbertura());
            f.setCep(fornecedorAtualizado.getCep());
            f.setLogradouro(fornecedorAtualizado.getLogradouro());
            f.setNumero(fornecedorAtualizado.getNumero());
            f.setComplemento(fornecedorAtualizado.getComplemento());
            f.setBairro(fornecedorAtualizado.getBairro());
            f.setCidade(fornecedorAtualizado.getCidade());
            f.setNacionalidade(fornecedorAtualizado.getNacionalidade());
            f.setLimiteCredito(fornecedorAtualizado.getLimiteCredito());
            f.setObservacao(fornecedorAtualizado.getObservacao());
            f.setAtivo(fornecedorAtualizado.getAtivo());
            f.setUsuarioAlteracao(fornecedorAtualizado.getUsuarioAlteracao());

            f.getEmails().clear();
            if (fornecedorAtualizado.getEmails() != null) {
                fornecedorAtualizado.getEmails().forEach(e -> {
                    e.setFornecedor(f);
                    f.getEmails().add(e);
                });
            }

            f.getTelefones().clear();
            if (fornecedorAtualizado.getTelefones() != null) {
                fornecedorAtualizado.getTelefones().forEach(t -> {
                    t.setFornecedor(f);
                    f.getTelefones().add(t);
                });
            }

            return fornecedorRepository.save(f);
        }).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado: " + id));
    }

    public void deletar(Long id) {
        if (!fornecedorRepository.existsById(id)) {
            throw new RuntimeException("Fornecedor não encontrado: " + id);
        }
        fornecedorRepository.deleteById(id);
    }

    public Fornecedor inativar(Long id) {
        return fornecedorRepository.findById(id).map(f -> {
            f.setAtivo(false);
            return fornecedorRepository.save(f);
        }).orElseThrow(() -> new RuntimeException("Fornecedor não encontrado: " + id));
    }

    private void validar(Fornecedor f) {
        if (f.getFornecedor() == null || f.getFornecedor().trim().isEmpty()) {
            throw new RuntimeException("Nome/Razão Social do fornecedor é obrigatório");
        }
        if (f.getTipo() == null || (f.getTipo() != 1 && f.getTipo() != 2)) {
            throw new RuntimeException("Tipo inválido. Use 1 (PF) ou 2 (PJ)");
        }
        if (f.getCpfCnpj() != null) {
            String limpo = f.getCpfCnpj().replaceAll("[^0-9]", "");
            if (f.getTipo() == 1 && limpo.length() != 11) {
                throw new RuntimeException("CPF deve ter 11 dígitos");
            }
            if (f.getTipo() == 2 && limpo.length() != 14) {
                throw new RuntimeException("CNPJ deve ter 14 dígitos");
            }
        }
    }

    private void vincularFilhos(Fornecedor f) {
        if (f.getEmails() != null) {
            for (FornecedorEmail e : f.getEmails()) {
                e.setFornecedor(f);
            }
        }
        if (f.getTelefones() != null) {
            for (FornecedorTelefone t : f.getTelefones()) {
                t.setFornecedor(f);
            }
        }
    }
}
