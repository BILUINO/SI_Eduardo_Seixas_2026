package com.praticapro.sieduardoseixas2026.unidademedida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/unidade-medida")
@CrossOrigin(origins = "*")
public class UnidadeMedidaController {

    @Autowired
    private UnidadeMedidaService unidadeMedidaService;

    @GetMapping
    public List<UnidadeMedida> listarTodos() {
        return unidadeMedidaService.listarTodos();
    }

    @GetMapping("/ativos")
    public List<UnidadeMedida> listarAtivos() {
        return unidadeMedidaService.listarAtivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeMedida> buscarPorId(@PathVariable Long id) {
        return unidadeMedidaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public UnidadeMedida salvar(@RequestBody UnidadeMedida unidadeMedida) {
        return unidadeMedidaService.salvar(unidadeMedida);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UnidadeMedida> atualizar(@PathVariable Long id, @RequestBody UnidadeMedida unidadeMedida) {
        try {
            return ResponseEntity.ok(unidadeMedidaService.atualizar(id, unidadeMedida));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        unidadeMedidaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
