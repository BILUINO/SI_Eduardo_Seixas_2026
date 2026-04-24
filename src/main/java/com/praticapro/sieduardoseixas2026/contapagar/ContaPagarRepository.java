package com.praticapro.sieduardoseixas2026.contapagar;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContaPagarRepository extends JpaRepository<ContaPagar, Long> {

    List<ContaPagar> findByStatus(String status);

    List<ContaPagar> findByFornecedorId(Long fornecedorId);

    List<ContaPagar> findByNotaEntradaId(Long notaEntradaId);

    List<ContaPagar> findByDataVencimentoBetween(LocalDate inicio, LocalDate fim);

    List<ContaPagar> findByStatusAndDataVencimentoBefore(String status, LocalDate data);
}
