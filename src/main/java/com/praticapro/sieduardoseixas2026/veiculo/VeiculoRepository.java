package com.praticapro.sieduardoseixas2026.veiculo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VeiculoRepository extends JpaRepository<Veiculo, Long> {

    List<Veiculo> findByAtivoTrue();

    Optional<Veiculo> findByPlaca(String placa);

    List<Veiculo> findByModeloContainingIgnoreCase(String modelo);
}
