package com.praticapro.sieduardoseixas2026.servico;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ServicoRepository extends JpaRepository<Servico, Long> {

    Optional<Servico> findByCodigo(String codigo);

    List<Servico> findByServicoContainingIgnoreCase(String nome);

    List<Servico> findByAtivoTrue();

    List<Servico> findByCategoriaId(Long categoriaId);

    boolean existsByCodigo(String codigo);
}
