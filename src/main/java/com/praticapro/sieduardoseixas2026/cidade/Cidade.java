package com.praticapro.sieduardoseixas2026.cidade;

import com.praticapro.sieduardoseixas2026.estado.Estado;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "codigo_ibge", length = 10)
    private String codigoIbge;

    @ManyToOne
    @JoinColumn(name = "estado_id", nullable = false)
    private Estado estado;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "data_cadastro", updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "ultima_modificacao")
    private LocalDateTime ultimaModificacao;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        this.ultimaModificacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.ultimaModificacao = LocalDateTime.now();
    }
}
