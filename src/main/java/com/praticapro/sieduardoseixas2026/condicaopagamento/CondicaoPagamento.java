package com.praticapro.sieduardoseixas2026.condicaopagamento;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_condicao_pagamento")
public class CondicaoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "condicao_pagamento", nullable = false, length = 100)
    private String condicaoPagamento;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "numero_parcelas", nullable = false)
    private Integer numeroParcelas = 1;

    @Column(name = "a_vista", nullable = false)
    private Boolean aVista = false;

    @Column(name = "percentual_juros", precision = 5, scale = 2)
    private java.math.BigDecimal percentualJuros = java.math.BigDecimal.ZERO;

    @Column(name = "percentual_multa", precision = 5, scale = 2)
    private java.math.BigDecimal percentualMulta = java.math.BigDecimal.ZERO;

    @Column(name = "percentual_desconto", precision = 5, scale = 2)
    private java.math.BigDecimal percentualDesconto = java.math.BigDecimal.ZERO;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @Column(name = "usuario_cadastro", length = 100)
    private String usuarioCadastro;

    @Column(name = "usuario_alteracao", length = 100)
    private String usuarioAlteracao;

    @OneToMany(mappedBy = "condicaoPagamento", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference("condicao-parcela")
    private List<ParcelaCondicaoPagamento> parcelas = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        if (this.ativo == null) this.ativo = true;
        if (this.aVista == null) this.aVista = false;
        if (this.numeroParcelas == null) this.numeroParcelas = 1;
        if (this.percentualJuros == null) this.percentualJuros = java.math.BigDecimal.ZERO;
        if (this.percentualMulta == null) this.percentualMulta = java.math.BigDecimal.ZERO;
        if (this.percentualDesconto == null) this.percentualDesconto = java.math.BigDecimal.ZERO;
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAlteracao = LocalDateTime.now();
    }
}
