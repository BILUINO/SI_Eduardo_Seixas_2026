package com.praticapro.sieduardoseixas2026.pais;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pais")
@CrossOrigin(origins = "*")
public class PaisController {

    @Autowired
    private PaisService paisService;

    @GetMapping
    public List<Pais> listarTodos() {
        return paisService.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Pais> buscarPorId(@PathVariable Long id) {
        return paisService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Pais salvar(@RequestBody Pais pais) {
        return paisService.salvar(pais);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Pais> atualizar(@PathVariable Long id, @RequestBody Pais pais) {
        try {
            return ResponseEntity.ok(paisService.atualizar(id, pais));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        paisService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
