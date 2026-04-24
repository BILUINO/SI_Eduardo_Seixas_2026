package com.praticapro.sieduardoseixas2026.notasaida;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.praticapro.sieduardoseixas2026.cliente.Cliente;
import com.praticapro.sieduardoseixas2026.condicaopagamento.CondicaoPagamento;
import com.praticapro.sieduardoseixas2026.modalidadenfe.ModalidadeNfe;
import com.praticapro.sieduardoseixas2026.transportadora.Transportadora;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_nota_saida", uniqueConstraints = {
        @UniqueConstraint(name = "uk_nota_saida_num_serie",
                columnNames = {"numero", "serie"})
})
public class NotaSaida {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "numero", nullable = false, length = 20)
    private String numero;

    @Column(name = "serie", nullable = false, length = 10)
    private String serie;

    @Column(name = "chave_acesso", length = 44)
    private String chaveAcesso;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "modalidade_nfe_id")
    private ModalidadeNfe modalidadeNfe;

    @Column(name = "data_emissao", nullable = false)
    private LocalDate dataEmissao;

    @Column(name = "data_saida", nullable = false)
    private LocalDate dataSaida;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cliente_id", nullable = false)
    private Cliente cliente;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transportadora_id")
    private Transportadora transportadora;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "condicao_pagamento_id")
    private CondicaoPagamento condicaoPagamento;

    @Column(name = "valor_produtos", precision = 15, scale = 2)
    private BigDecimal valorProdutos = BigDecimal.ZERO;

    @Column(name = "valor_frete", precision = 15, scale = 2)
    private BigDecimal valorFrete = BigDecimal.ZERO;

    @Column(name = "valor_seguro", precision = 15, scale = 2)
    private BigDecimal valorSeguro = BigDecimal.ZERO;

    @Column(name = "valor_outras_despesas", precision = 15, scale = 2)
    private BigDecimal valorOutrasDespesas = BigDecimal.ZERO;

    @Column(name = "valor_desconto", precision = 15, scale = 2)
    private BigDecimal valorDesconto = BigDecimal.ZERO;

    @Column(name = "valor_icms", precision = 15, scale = 2)
    private BigDecimal valorIcms = BigDecimal.ZERO;

    @Column(name = "valor_ipi", precision = 15, scale = 2)
    private BigDecimal valorIpi = BigDecimal.ZERO;

    @Column(name = "valor_total", precision = 15, scale = 2)
    private BigDecimal valorTotal = BigDecimal.ZERO;

    @Column(name = "status", nullable = false, length = 20)
    private String status = "PENDENTE";

    @Column(name = "observacao", length = 500)
    private String observacao;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @Column(name = "data_confirmacao")
    private LocalDateTime dataConfirmacao;

    @Column(name = "data_cancelamento")
    private LocalDateTime dataCancelamento;

    @Column(name = "usuario_cadastro", length = 100)
    private String usuarioCadastro;

    @Column(name = "usuario_alteracao", length = 100)
    private String usuarioAlteracao;

    @OneToMany(mappedBy = "notaSaida", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference("nota-saida-item")
    private List<ItemNotaSaida> itens = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        if (this.status == null) this.status = "PENDENTE";
        calcularTotais();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAlteracao = LocalDateTime.now();
        calcularTotais();
    }

    public void calcularTotais() {
        if (this.itens != null && !this.itens.isEmpty()) {
            this.valorProdutos = this.itens.stream()
                    .map(i -> i.getValorTotal() != null ? i.getValorTotal() : BigDecimal.ZERO)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
        } else {
            this.valorProdutos = BigDecimal.ZERO;
        }

        this.valorTotal = this.valorProdutos
                .add(nvl(this.valorFrete))
                .add(nvl(this.valorSeguro))
                .add(nvl(this.valorOutrasDespesas))
                .add(nvl(this.valorIpi))
                .subtract(nvl(this.valorDesconto));
    }

    private BigDecimal nvl(BigDecimal v) {
        return v == null ? BigDecimal.ZERO : v;
    }
}
