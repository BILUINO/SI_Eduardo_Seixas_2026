package com.praticapro.sieduardoseixas2026.cliente;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByAtivoTrue();

    Optional<Cliente> findByCpfCnpj(String cpfCnpj);

    List<Cliente> findByNomeContainingIgnoreCase(String nome);

    List<Cliente> findByTipo(Integer tipo);
}
