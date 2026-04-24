package com.praticapro.sieduardoseixas2026.estado;

import com.praticapro.sieduardoseixas2026.pais.Pais;
import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_estado")
public class Estado {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 2)
    private String uf;

    @ManyToOne
    @JoinColumn(name = "pais_id", nullable = false)
    private Pais pais;

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
