package com.praticapro.sieduardoseixas2026.viacep;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ViaCepResponse {

    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;

    @JsonProperty("erro")
    private Boolean erro;
}
