package com.praticapro.sieduardoseixas2026.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioService {

    @Autowired
    private FuncionarioRepository funcionarioRepository;

    public List<Funcionario> listarTodos() {
        return funcionarioRepository.findAll();
    }

    public List<Funcionario> listarAtivos() {
        return funcionarioRepository.findByAtivoTrue();
    }

    public List<Funcionario> listarEmpregados() {
        return funcionarioRepository.findByDataDemissaoIsNull();
    }

    public Optional<Funcionario> buscarPorId(Long id) {
        return funcionarioRepository.findById(id);
    }

    public Optional<Funcionario> buscarPorCpf(String cpf) {
        String limpo = cpf == null ? null : cpf.replaceAll("[^0-9]", "");
        return funcionarioRepository.findByCpf(limpo);
    }

    public Optional<Funcionario> buscarPorMatricula(String matricula) {
        return funcionarioRepository.findByMatricula(matricula);
    }

    public List<Funcionario> buscarPorNome(String nome) {
        return funcionarioRepository.findByFuncionarioContainingIgnoreCase(nome);
    }

    public List<Funcionario> buscarPorFuncao(Long funcaoId) {
        return funcionarioRepository.findByFuncaoFuncionarioId(funcaoId);
    }

    public Funcionario salvar(Funcionario f) {
        validar(f);
        String cpfLimpo = f.getCpf().replaceAll("[^0-9]", "");
        if (funcionarioRepository.existsByCpf(cpfLimpo)) {
            throw new RuntimeException("Já existe um funcionário cadastrado com este CPF");
        }
        if (f.getMatricula() != null && !f.getMatricula().isEmpty()
                && funcionarioRepository.existsByMatricula(f.getMatricula())) {
            throw new RuntimeException("Já existe um funcionário com esta matrícula");
        }
        return funcionarioRepository.save(f);
    }

    public Funcionario atualizar(Long id, Funcionario atualizado) {
        return funcionarioRepository.findById(id).map(f -> {
            validar(atualizado);

            String cpfLimpo = atualizado.getCpf().replaceAll("[^0-9]", "");
            if (!cpfLimpo.equals(f.getCpf()) && funcionarioRepository.existsByCpf(cpfLimpo)) {
                throw new RuntimeException("Já existe outro funcionário com este CPF");
            }
            if (atualizado.getMatricula() != null
                    && !atualizado.getMatricula().equals(f.getMatricula())
                    && funcionarioRepository.existsByMatricula(atualizado.getMatricula())) {
                throw new RuntimeException("Já existe outro funcionário com esta matrícula");
            }

            f.setFuncionario(atualizado.getFuncionario());
            f.setApelido(atualizado.getApelido());
            f.setMatricula(atualizado.getMatricula());
            f.setCpf(atualizado.getCpf());
            f.setRg(atualizado.getRg());
            f.setDataNascimento(atualizado.getDataNascimento());
            f.setSexo(atualizado.getSexo());
            f.setEstadoCivil(atualizado.getEstadoCivil());
            f.setEmail(atualizado.getEmail());
            f.setTelefone(atualizado.getTelefone());
            f.setCep(atualizado.getCep());
            f.setLogradouro(atualizado.getLogradouro());
            f.setNumero(atualizado.getNumero());
            f.setComplemento(atualizado.getComplemento());
            f.setBairro(atualizado.getBairro());
            f.setCidade(atualizado.getCidade());
            f.setNacionalidade(atualizado.getNacionalidade());
            f.setFuncaoFuncionario(atualizado.getFuncaoFuncionario());
            f.setSalario(atualizado.getSalario());
            f.setDataAdmissao(atualizado.getDataAdmissao());
            f.setDataDemissao(atualizado.getDataDemissao());
            f.setCnh(atualizado.getCnh());
            f.setCategoriaCnh(atualizado.getCategoriaCnh());
            f.setValidadeCnh(atualizado.getValidadeCnh());
            f.setObservacao(atualizado.getObservacao());
            f.setAtivo(atualizado.getAtivo());
            f.setUsuarioAlteracao(atualizado.getUsuarioAlteracao());

            return funcionarioRepository.save(f);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado: " + id));
    }

    public void deletar(Long id) {
        if (!funcionarioRepository.existsById(id)) {
            throw new RuntimeException("Funcionário não encontrado: " + id);
        }
        funcionarioRepository.deleteById(id);
    }

    public Funcionario inativar(Long id) {
        return funcionarioRepository.findById(id).map(f -> {
            f.setAtivo(false);
            return funcionarioRepository.save(f);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado: " + id));
    }

    public Funcionario demitir(Long id, LocalDate dataDemissao) {
        return funcionarioRepository.findById(id).map(f -> {
            if (f.getDataDemissao() != null) {
                throw new RuntimeException("Funcionário já está demitido");
            }
            LocalDate data = dataDemissao == null ? LocalDate.now() : dataDemissao;
            if (data.isBefore(f.getDataAdmissao())) {
                throw new RuntimeException("Data de demissão não pode ser anterior à admissão");
            }
            f.setDataDemissao(data);
            f.setAtivo(false);
            return funcionarioRepository.save(f);
        }).orElseThrow(() -> new RuntimeException("Funcionário não encontrado: " + id));
    }

    private void validar(Funcionario f) {
        if (f.getFuncionario() == null || f.getFuncionario().trim().isEmpty()) {
            throw new RuntimeException("Nome do funcionário é obrigatório");
        }
        if (f.getCpf() == null || f.getCpf().trim().isEmpty()) {
            throw new RuntimeException("CPF é obrigatório");
        }
        String cpfLimpo = f.getCpf().replaceAll("[^0-9]", "");
        if (cpfLimpo.length() != 11) {
            throw new RuntimeException("CPF deve ter 11 dígitos");
        }
        if (f.getFuncaoFuncionario() == null || f.getFuncaoFuncionario().getId() == null) {
            throw new RuntimeException("Função do funcionário é obrigatória");
        }
        if (f.getDataAdmissao() == null) {
            throw new RuntimeException("Data de admissão é obrigatória");
        }
        if (f.getDataDemissao() != null && f.getDataDemissao().isBefore(f.getDataAdmissao())) {
            throw new RuntimeException("Data de demissão não pode ser anterior à admissão");
        }
        if (f.getDataNascimento() != null && f.getDataNascimento().isAfter(LocalDate.now())) {
            throw new RuntimeException("Data de nascimento não pode ser futura");
        }
    }
}
