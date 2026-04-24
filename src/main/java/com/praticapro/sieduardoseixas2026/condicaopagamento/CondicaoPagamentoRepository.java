package com.praticapro.sieduardoseixas2026.condicaopagamento;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CondicaoPagamentoRepository extends JpaRepository<CondicaoPagamento, Long> {

    List<CondicaoPagamento> findByCondicaoPagamentoContainingIgnoreCase(String nome);

    List<CondicaoPagamento> findByAtivoTrue();

    List<CondicaoPagamento> findByAVistaTrue();

    Optional<CondicaoPagamento> findByCondicaoPagamentoIgnoreCase(String nome);
}
