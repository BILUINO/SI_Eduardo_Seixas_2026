package com.praticapro.sieduardoseixas2026.marca;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MarcaRepository extends JpaRepository<Marca, Long> {

    List<Marca> findByAtivoTrue();

    List<Marca> findByNomeContainingIgnoreCase(String nome);
}
