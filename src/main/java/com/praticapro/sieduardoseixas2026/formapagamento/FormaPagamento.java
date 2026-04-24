package com.praticapro.sieduardoseixas2026.formapagamento;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_forma_pagamento")
public class FormaPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(nullable = false, length = 100)
    private String descricao;

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
