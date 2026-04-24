package com.praticapro.sieduardoseixas2026.notasaida;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.praticapro.sieduardoseixas2026.produto.Produto;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Entity
@Table(name = "tb_item_nota_saida")
public class ItemNotaSaida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nota_saida_id", nullable = false)
    @JsonBackReference("nota-saida-item")
    private NotaSaida notaSaida;

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
