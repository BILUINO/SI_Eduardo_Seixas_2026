package com.praticapro.sieduardoseixas2026.funcionario;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FuncaoFuncionarioRepository extends JpaRepository<FuncaoFuncionario, Long> {

    List<FuncaoFuncionario> findByAtivoTrue();

    List<FuncaoFuncionario> findByNomeContainingIgnoreCase(String nome);

    List<FuncaoFuncionario> findByRequerCnhTrue();
}
