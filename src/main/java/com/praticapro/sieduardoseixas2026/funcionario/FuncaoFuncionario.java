package com.praticapro.sieduardoseixas2026.funcionario;

import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_funcao_funcionario")
public class FuncaoFuncionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "funcao_funcionario", nullable = false, length = 255)
    private String nome;

    @Column(length = 255)
    private String descricao;

    @Column(name = "salario_base", precision = 10, scale = 2)
    private BigDecimal salarioBase = BigDecimal.ZERO;

    @Column(name = "carga_horaria", nullable = false, precision = 10, scale = 2)
    private BigDecimal cargaHoraria;

    @Column(name = "requer_cnh")
    private Boolean requerCnh = false;

    @Column(length = 255)
    private String observacao;

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
