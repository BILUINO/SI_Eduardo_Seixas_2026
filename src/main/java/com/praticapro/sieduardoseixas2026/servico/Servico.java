package com.praticapro.sieduardoseixas2026.servico;

import com.praticapro.sieduardoseixas2026.categoria.Categoria;
import com.praticapro.sieduardoseixas2026.unidademedida.UnidadeMedida;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_servico", uniqueConstraints = {
        @UniqueConstraint(name = "uk_servico_codigo", columnNames = "codigo")
})
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "codigo", length = 50)
    private String codigo;

    @Column(name = "servico", nullable = false, length = 255)
    private String servico;

    @Column(name = "descricao", length = 500)
    private String descricao;

    @Column(name = "preco", nullable = false, precision = 15, scale = 2)
    private BigDecimal preco;

    @Column(name = "custo", precision = 15, scale = 2)
    private BigDecimal custo = BigDecimal.ZERO;

    @Column(name = "percentual_lucro", precision = 5, scale = 2)
    private BigDecimal percentualLucro;

    @Column(name = "tempo_estimado_minutos")
    private Integer tempoEstimadoMinutos;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "unidade_medida_id")
    private UnidadeMedida unidadeMedida;

    @Column(name = "observacao", length = 500)
    private String observacao;

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

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        if (this.ativo == null) this.ativo = true;
        if (this.custo == null) this.custo = BigDecimal.ZERO;
        calcularPercentualLucro();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAlteracao = LocalDateTime.now();
        calcularPercentualLucro();
    }

    private void calcularPercentualLucro() {
        if (this.preco != null && this.custo != null && this.custo.compareTo(BigDecimal.ZERO) > 0) {
            this.percentualLucro = this.preco.subtract(this.custo)
                    .divide(this.custo, 4, java.math.RoundingMode.HALF_UP)
                    .multiply(new BigDecimal("100"))
                    .setScale(2, java.math.RoundingMode.HALF_UP);
        } else {
            this.percentualLucro = BigDecimal.ZERO;
        }
    }
}
