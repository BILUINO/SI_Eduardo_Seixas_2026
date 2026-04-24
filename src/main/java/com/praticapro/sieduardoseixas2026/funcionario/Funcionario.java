package com.praticapro.sieduardoseixas2026.funcionario;

import com.praticapro.sieduardoseixas2026.cidade.Cidade;
import com.praticapro.sieduardoseixas2026.pais.Pais;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_funcionario", uniqueConstraints = {
        @UniqueConstraint(name = "uk_funcionario_cpf", columnNames = "cpf"),
        @UniqueConstraint(name = "uk_funcionario_matricula", columnNames = "matricula")
})
public class Funcionario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "funcionario", nullable = false, length = 255)
    private String funcionario;

    @Column(name = "apelido", length = 255)
    private String apelido;

    @Column(name = "matricula", length = 20)
    private String matricula;

    @Column(name = "cpf", nullable = false, length = 11)
    private String cpf;

    @Column(name = "rg", length = 20)
    private String rg;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "sexo", length = 1)
    private String sexo;

    @Column(name = "estado_civil", length = 1)
    private String estadoCivil;

    @Column(name = "email", length = 255)
    private String email;

    @Column(name = "telefone", length = 20)
    private String telefone;

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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "funcao_funcionario_id", nullable = false)
    private FuncaoFuncionario funcaoFuncionario;

    @Column(name = "salario", precision = 15, scale = 2)
    private BigDecimal salario;

    @Column(name = "data_admissao", nullable = false)
    private LocalDate dataAdmissao;

    @Column(name = "data_demissao")
    private LocalDate dataDemissao;

    @Column(name = "cnh", length = 20)
    private String cnh;

    @Column(name = "categoria_cnh", length = 5)
    private String categoriaCnh;

    @Column(name = "validade_cnh")
    private LocalDate validadeCnh;

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
        limparMascaras();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAlteracao = LocalDateTime.now();
        limparMascaras();
    }

    private void limparMascaras() {
        if (this.cpf != null) this.cpf = this.cpf.replaceAll("[^0-9]", "");
        if (this.cep != null) this.cep = this.cep.replaceAll("[^0-9]", "");
        if (this.telefone != null) this.telefone = this.telefone.replaceAll("[^0-9]", "");
    }
}
