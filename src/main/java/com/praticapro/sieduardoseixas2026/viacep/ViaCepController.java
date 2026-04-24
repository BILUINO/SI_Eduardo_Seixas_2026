package com.praticapro.sieduardoseixas2026.viacep;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/viacep")
@CrossOrigin(origins = "*")
public class ViaCepController {

    @Autowired
    private ViaCepService viaCepService;

    @GetMapping("/{cep}")
    public ResponseEntity<ViaCepResponse> buscarCep(@PathVariable String cep) {
        try {
            ViaCepResponse response = viaCepService.buscarCep(cep);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}
