package com.praticapro.sieduardoseixas2026.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByCpf(String cpf);

    Optional<Funcionario> findByMatricula(String matricula);

    List<Funcionario> findByFuncionarioContainingIgnoreCase(String nome);

    List<Funcionario> findByAtivoTrue();

    List<Funcionario> findByDataDemissaoIsNull();

    List<Funcionario> findByFuncaoFuncionarioId(Long funcaoId);

    boolean existsByCpf(String cpf);

    boolean existsByMatricula(String matricula);
}
