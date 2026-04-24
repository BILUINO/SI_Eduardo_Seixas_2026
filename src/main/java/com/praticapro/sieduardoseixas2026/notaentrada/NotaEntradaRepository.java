package com.praticapro.sieduardoseixas2026.notaentrada;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotaEntradaRepository extends JpaRepository<NotaEntrada, Long> {

    List<NotaEntrada> findByFornecedorId(Long fornecedorId);

    List<NotaEntrada> findByStatus(String status);

    List<NotaEntrada> findByDataEntradaBetween(LocalDate inicio, LocalDate fim);

    Optional<NotaEntrada> findByNumeroAndSerieAndFornecedorId(String numero, String serie, Long fornecedorId);

    boolean existsByNumeroAndSerieAndFornecedorId(String numero, String serie, Long fornecedorId);
}
