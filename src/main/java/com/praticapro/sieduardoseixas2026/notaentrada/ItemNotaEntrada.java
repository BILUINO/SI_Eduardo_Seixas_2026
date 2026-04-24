package com.praticapro.sieduardoseixas2026.notaentrada;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.praticapro.sieduardoseixas2026.produto.Produto;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb_item_nota_entrada")
public class ItemNotaEntrada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nota_entrada_id", nullable = false)
    @JsonBackReference("nota-entrada-item")
    private NotaEntrada notaEntrada;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "produto_id", nullable = false)
    private Produto produto;

    @Column(name = "quantidade", nullable = false, precision = 15, scale = 3)
    private BigDecimal quantidade;

    @Column(name = "valor_unitario", nullable = false, precision = 15, scale = 4)
    private BigDecimal valorUnitario;

    @Column(name = "valor_desconto", precision = 15, scale = 2)
    private BigDecimal valorDesconto = BigDecimal.ZERO;

    @Column(name = "valor_total", precision = 15, scale = 2)
    private BigDecimal valorTotal;

    @Column(name = "observacao", length = 255)
    private String observacao;

    @PrePersist
    @PreUpdate
    public void calcularTotal() {
        if (this.quantidade != null && this.valorUnitario != null) {
            BigDecimal total = this.quantidade.multiply(this.valorUnitario);
            if (this.valorDesconto != null) {
                total = total.subtract(this.valorDesconto);
            }
            this.valorTotal = total.setScale(2, java.math.RoundingMode.HALF_UP);
        }
        if (this.valorDesconto == null) this.valorDesconto = BigDecimal.ZERO;
    }
}
