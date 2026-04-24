package com.praticapro.sieduardoseixas2026.produto;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByAtivoTrue();

    Optional<Produto> findByCodigoBarras(String codigoBarras);

    List<Produto> findByNomeContainingIgnoreCase(String nome);

    List<Produto> findByMarcaId(Long marcaId);

    List<Produto> findByCategoriaId(Long categoriaId);

    List<Produto> findByQuantidadeLessThanEqual(Integer quantidade);
}
