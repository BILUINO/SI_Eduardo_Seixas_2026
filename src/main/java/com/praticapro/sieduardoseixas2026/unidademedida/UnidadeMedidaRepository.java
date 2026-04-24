package com.praticapro.sieduardoseixas2026.unidademedida;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UnidadeMedidaRepository extends JpaRepository<UnidadeMedida, Long> {

    List<UnidadeMedida> findByAtivoTrue();

    List<UnidadeMedida> findByNomeContainingIgnoreCase(String nome);
}
