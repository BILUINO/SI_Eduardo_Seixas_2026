package com.praticapro.sieduardoseixas2026.modalidadenfe;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ModalidadeNfeRepository extends JpaRepository<ModalidadeNfe, Long> {

    List<ModalidadeNfe> findByAtivoTrue();

    Optional<ModalidadeNfe> findByCodigo(String codigo);

    List<ModalidadeNfe> findByDescricaoContainingIgnoreCase(String descricao);
}
