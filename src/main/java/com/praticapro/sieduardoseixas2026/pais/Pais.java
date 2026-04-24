package com.praticapro.sieduardoseixas2026.pais;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_pais")
public class Pais {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 5)
    private String sigla;

    @Column(length = 5)
    private String codigo;

    @Column(length = 100)
    private String nacionalidade;

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
    public void preUpdate(){
        this.ultimaModificacao = LocalDateTime.now();
    }
}
