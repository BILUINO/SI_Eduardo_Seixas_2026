package com.praticapro.sieduardoseixas2026.formapagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormaPagamentoRepository extends JpaRepository<FormaPagamento, Long> {

    List<FormaPagamento> findByAtivoTrue();

    List<FormaPagamento> findByNomeContainingIgnoreCase(String nome);
}
