package com.praticapro.sieduardoseixas2026.transportadora;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransportadoraRepository extends JpaRepository<Transportadora, Long> {

    Optional<Transportadora> findByCpfCnpj(String cpfCnpj);

    List<Transportadora> findByTransportadoraContainingIgnoreCase(String nome);

    List<Transportadora> findByApelidoContainingIgnoreCase(String apelido);

    List<Transportadora> findByAtivoTrue();

    List<Transportadora> findByTipo(Integer tipo);

    boolean existsByCpfCnpj(String cpfCnpj);
}
