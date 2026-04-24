package com.praticapro.sieduardoseixas2026.viacep;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class ViaCepService {

    private final RestTemplate restTemplate = new RestTemplate();

    public ViaCepResponse buscarCep(String cep) {
        String cepLimpo = cep.replaceAll("[^0-9]", "");

        if (cepLimpo.length() != 8) {
            throw new RuntimeException("CEP inválido: " + cep);
        }

        String url = "https://viacep.com.br/ws/" + cepLimpo + "/json/";

        ViaCepResponse response = restTemplate.getForObject(url, ViaCepResponse.class);

        if (response == null || Boolean.TRUE.equals(response.getErro())) {
            throw new RuntimeException("CEP não encontrado: " + cep);
        }

        return response;
    }
}
