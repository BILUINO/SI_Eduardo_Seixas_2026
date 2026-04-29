package com.praticapro.sieduardoseixas2026.cliente;

import com.praticapro.sieduardoseixas2026.cidade.Cidade;
import com.praticapro.sieduardoseixas2026.condicaopagamento.CondicaoPagamento;
import com.praticapro.sieduardoseixas2026.pais.Pais;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_cliente", uniqueConstraints = {
        @UniqueConstraint(name = "uk_cliente_cpf_cnpj", columnNames = "cpf_cnpj")
})
public class Cliente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; //oi

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(length = 60)
    private String apelido;

    private Integer tipo;

    @Column(name = "cpf_cnpj", length = 14)
    private String cpfCnpj;

    @Column(name = "rg_inscricao_estadual", length = 14)
    private String rgInscricaoEstadual;

    @Column(length = 1)
    private String sexo;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "estado_civil", length = 255)
    private String estadoCivil;

    @Column(length = 100)
    private String email;

    @Column(length = 20)
    private String telefone;

    @Column(length = 200)
    private String endereco;

    @Column(length = 5)
    private String numero;

    @Column(length = 100)
    private String complemento;

    @Column(length = 50)
    private String bairro;

    @Column(length = 9)
    private String cep;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nacionalidade_id")
    private Pais nacionalidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "condicao_pagamento_id")
    private CondicaoPagamento condicaoPagamento;

    @Column(name = "limite_credito", precision = 10, scale = 2)
    private BigDecimal limiteCredito = BigDecimal.ZERO;

    @Column(length = 255)
    private String observacao;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.dataAlteracao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAlteracao = LocalDateTime.now();
    }
}
