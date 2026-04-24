package com.praticapro.sieduardoseixas2026.notasaida;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface NotaSaidaRepository extends JpaRepository<NotaSaida, Long> {

    List<NotaSaida> findByClienteId(Long clienteId);

    List<NotaSaida> findByStatus(String status);

    List<NotaSaida> findByDataSaidaBetween(LocalDate inicio, LocalDate fim);

    Optional<NotaSaida> findByNumeroAndSerie(String numero, String serie);

    boolean existsByNumeroAndSerie(String numero, String serie);
}
