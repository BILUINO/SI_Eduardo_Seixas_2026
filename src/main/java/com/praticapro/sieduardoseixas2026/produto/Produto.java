package com.praticapro.sieduardoseixas2026.produto;

import com.praticapro.sieduardoseixas2026.categoria.Categoria;
import com.praticapro.sieduardoseixas2026.marca.Marca;
import com.praticapro.sieduardoseixas2026.unidademedida.UnidadeMedida;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 255)
    private String nome;

    @Column(name = "codigo_barras", length = 255)
    private String codigoBarras;

    @Column(length = 10)
    private String referencia;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "marca_id", nullable = false)
    private Marca marca;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unidade_medida_id", nullable = false)
    private UnidadeMedida unidadeMedida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @Column(name = "valor_compra", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorCompra;

    @Column(name = "valor_venda", nullable = false, precision = 10, scale = 2)
    private BigDecimal valorVenda;

    @Column(name = "quantidade", precision = 15, scale = 3)
    private BigDecimal quantidade = BigDecimal.ZERO;

    @Column(name = "quantidade_minima", precision = 15, scale = 3)
    private BigDecimal quantidadeMinima = BigDecimal.ZERO;

    @Column(name = "percentual_lucro", nullable = false, precision = 10, scale = 2)
    private BigDecimal percentualLucro;

    @Column(name = "valor_ultima_compra", precision = 15, scale = 4)
    private BigDecimal valorUltimaCompra;

    @Column(name = "data_ultima_compra")
    private LocalDate dataUltimaCompra;

    @Column(columnDefinition = "TEXT")
    private String descricao;

    @Column(length = 255)
    private String observacoes;

    @Column(nullable = false)
    private Boolean ativo = true;

    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;

    @Column(name = "ultima_modificacao")
    private LocalDateTime ultimaModificacao;

    @Column(name = "valor_ultima_venda", precision = 15, scale = 4)
    private BigDecimal valorUltimaVenda;

    @Column(name = "data_ultima_venda")
    private LocalDate dataUltimaVenda;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
        this.ultimaModificacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.ultimaModificacao = LocalDateTime.now();
    }
}
