package com.praticapro.sieduardoseixas2026.fornecedor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FornecedorRepository extends JpaRepository<Fornecedor, Long> {

    Optional<Fornecedor> findByCpfCnpj(String cpfCnpj);

    List<Fornecedor> findByFornecedorContainingIgnoreCase(String fornecedor);

    List<Fornecedor> findByApelidoContainingIgnoreCase(String apelido);

    List<Fornecedor> findByAtivoTrue();

    List<Fornecedor> findByTipo(Integer tipo);

    boolean existsByCpfCnpj(String cpfCnpj);
}
