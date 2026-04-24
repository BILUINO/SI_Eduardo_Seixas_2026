package com.praticapro.sieduardoseixas2026.transportadora;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "tb_transportadora_email")
public class TransportadoraEmail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transportadora_id", nullable = false)
    @JsonBackReference("transportadora-email")
    private Transportadora transportadora;

    @Column(name = "email", nullable = false, length = 255)
    private String email;

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
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAlteracao = LocalDateTime.now();
    }
}
