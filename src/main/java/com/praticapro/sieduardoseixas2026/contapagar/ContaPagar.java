package com.praticapro.sieduardoseixas2026.contapagar;

import com.praticapro.sieduardoseixas2026.formapagamento.FormaPagamento;
import com.praticapro.sieduardoseixas2026.fornecedor.Fornecedor;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_conta_pagar")
public class ContaPagar {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero_documento", length = 50)
    private String numeroDocumento;

    @Column(name = "numero_parcela", nullable = false)
    private Integer numeroParcela = 1;

    @Column(name = "total_parcelas", nullable = false)
    private Integer totalParcelas = 1;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fornecedor_id")
    private Fornecedor fornecedor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "forma_pagamento_id")
    private FormaPagamento formaPagamento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nota_entrada_id")
    private com.praticapro.sieduardoseixas2026.notaentrada.NotaEntrada notaEntrada;

    @Column(name = "descricao", length = 255)
    private String descricao;

    @Column(name = "valor_original", nullable = false, precision = 15, scale = 2)
    private BigDecimal valorOriginal;

    @Column(name = "valor_juros", precision = 15, scale = 2)
    private BigDecimal valorJuros = BigDecimal.ZERO;

    @Column(name = "valor_multa", precision = 15, scale = 2)
    private BigDecimal valorMulta = BigDecimal.ZERO;

    @Column(name = "valor_desconto", precision = 15, scale = 2)
    private BigDecimal valorDesconto = BigDecimal.ZERO;

    @Column(name = "valor_pago", precision = 15, scale = 2)
    private BigDecimal valorPago = BigDecimal.ZERO;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "data_vencimento", nullable = false)
    private LocalDate dataVencimento;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "status", nullable = false, length = 20)
    private String status = "ABERTA";

    @Column(name = "observacao", length = 500)
    private String observacao;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @Column(name = "usuario_cadastro", length = 100)
    private String usuarioCadastro;

    @Column(name = "usuario_alteracao", length = 100)
    private String usuarioAlteracao;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        if (this.status == null) this.status = "ABERTA";
        if (this.valorJuros == null) this.valorJuros = BigDecimal.ZERO;
        if (this.valorMulta == null) this.valorMulta = BigDecimal.ZERO;
        if (this.valorDesconto == null) this.valorDesconto = BigDecimal.ZERO;
        if (this.valorPago == null) this.valorPago = BigDecimal.ZERO;
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAlteracao = LocalDateTime.now();
    }

    @Transient
    public BigDecimal getValorAtualizado() {
        return (valorOriginal == null ? BigDecimal.ZERO : valorOriginal)
                .add(valorJuros == null ? BigDecimal.ZERO : valorJuros)
                .add(valorMulta == null ? BigDecimal.ZERO : valorMulta)
                .subtract(valorDesconto == null ? BigDecimal.ZERO : valorDesconto);
    }

    @Transient
    public BigDecimal getSaldo() {
        return getValorAtualizado().subtract(valorPago == null ? BigDecimal.ZERO : valorPago);
    }

    @Transient
    public boolean isVencida() {
        return "ABERTA".equals(status) && dataVencimento != null && dataVencimento.isBefore(LocalDate.now());
    }
}
