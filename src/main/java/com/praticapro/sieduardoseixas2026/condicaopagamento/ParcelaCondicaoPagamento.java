package com.praticapro.sieduardoseixas2026.condicaopagamento;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.praticapro.sieduardoseixas2026.formapagamento.FormaPagamento;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_parcela_condicao_pagamento")
public class ParcelaCondicaoPagamento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "condicao_pagamento_id", nullable = false)
    @JsonBackReference("condicao-parcela")
    private CondicaoPagamento condicaoPagamento;

    @Column(name = "numero_parcela", nullable = false)
    private Integer numeroParcela;

    @Column(name = "dias", nullable = false)
    private Integer dias;

    @Column(name = "percentual", nullable = false, precision = 5, scale = 2)
    private BigDecimal percentual;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "forma_pagamento_id", nullable = false)
    private FormaPagamento formaPagamento;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAlteracao = LocalDateTime.now();
    }
}
