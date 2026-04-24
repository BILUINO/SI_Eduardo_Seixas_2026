package com.praticapro.sieduardoseixas2026.categoria;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    List<Categoria> findByAtivoTrue();

    List<Categoria> findByNomeContainingIgnoreCase(String nome);
}
