package com.praticapro.sieduardoseixas2026.fornecedor;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_fornecedor_telefone")
public class FornecedorTelefone {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "fornecedor_id", nullable = false)
    @JsonBackReference("fornecedor-telefone")
    private Fornecedor fornecedor;

    @Column(name = "telefone", nullable = false, length = 20)
    private String telefone;

    @Column(name = "tipo", length = 50)
    private String tipo;

    @Column(name = "principal", nullable = false)
    private Boolean principal = false;

    @Column(name = "ativo", nullable = false)
    private Boolean ativo = true;

    @Column(name = "data_cadastro", nullable = false, updatable = false)
    private LocalDateTime dataCadastro;

    @Column(name = "data_alteracao")
    private LocalDateTime dataAlteracao;

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        if (this.ativo == null) this.ativo = true;
        if (this.principal == null) this.principal = false;
        limparMascara();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAlteracao = LocalDateTime.now();
        limparMascara();
    }

    private void limparMascara() {
        if (this.telefone != null) this.telefone = this.telefone.replaceAll("[^0-9]", "");
    }
}
