package com.praticapro.sieduardoseixas2026.transportadora;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.praticapro.sieduardoseixas2026.cidade.Cidade;
import com.praticapro.sieduardoseixas2026.pais.Pais;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table(name = "tb_transportadora", uniqueConstraints = {
        @UniqueConstraint(name = "uk_transportadora_cpf_cnpj", columnNames = "cpf_cnpj")
})
public class Transportadora {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "transportadora", nullable = false, length = 255)
    private String transportadora;

    @Column(name = "apelido", length = 255)
    private String apelido;

    @Column(name = "tipo", nullable = false)
    private Integer tipo;

    @Column(name = "cpf_cnpj", length = 14)
    private String cpfCnpj;

    @Column(name = "rg_ie", length = 20)
    private String rgIe;

    @Column(name = "antt", length = 20)
    private String antt;

    @Column(name = "cep", length = 8)
    private String cep;

    @Column(name = "logradouro", length = 255)
    private String logradouro;

    @Column(name = "numero", length = 20)
    private String numero;

    @Column(name = "complemento", length = 100)
    private String complemento;

    @Column(name = "bairro", length = 100)
    private String bairro;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cidade_id")
    private Cidade cidade;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "nacionalidade_id")
    private Pais nacionalidade;

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

    @OneToMany(mappedBy = "transportadora", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference("transportadora-email")
    private List<TransportadoraEmail> emails = new ArrayList<>();

    @OneToMany(mappedBy = "transportadora", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    @JsonManagedReference("transportadora-telefone")
    private List<TransportadoraTelefone> telefones = new ArrayList<>();

    @PrePersist
    public void prePersist() {
        this.dataCadastro = LocalDateTime.now();
        if (this.ativo == null) this.ativo = true;
        limparMascaras();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAlteracao = LocalDateTime.now();
        limparMascaras();
    }

    private void limparMascaras() {
        if (this.cpfCnpj != null) this.cpfCnpj = this.cpfCnpj.replaceAll("[^0-9]", "");
        if (this.cep != null) this.cep = this.cep.replaceAll("[^0-9]", "");
    }
}
