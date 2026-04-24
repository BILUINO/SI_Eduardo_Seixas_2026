package com.praticapro.sieduardoseixas2026.contareceber;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ContaReceberRepository extends JpaRepository<ContaReceber, Long> {

    List<ContaReceber> findByStatus(String status);

    List<ContaReceber> findByClienteId(Long clienteId);

    List<ContaReceber> findByNotaSaidaId(Long notaSaidaId);

    List<ContaReceber> findByDataVencimentoBetween(LocalDate inicio, LocalDate fim);

    List<ContaReceber> findByStatusAndDataVencimentoBefore(String status, LocalDate data);
}
