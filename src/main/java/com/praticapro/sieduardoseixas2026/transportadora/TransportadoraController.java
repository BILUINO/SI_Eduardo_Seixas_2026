package com.praticapro.sieduardoseixas2026.transportadora;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/transportadora")
@CrossOrigin(origins = "*")
public class TransportadoraController {

    @Autowired
    private TransportadoraService transportadoraService;

    @GetMapping
    public ResponseEntity<List<Transportadora>> listarTodos() {
        return ResponseEntity.ok(transportadoraService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Transportadora>> listarAtivos() {
        return ResponseEntity.ok(transportadoraService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Transportadora> buscarPorId(@PathVariable Long id) {
        return transportadoraService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    public ResponseEntity<Transportadora> buscarPorCpfCnpj(@PathVariable String cpfCnpj) {
        return transportadoraService.buscarPorCpfCnpj(cpfCnpj)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Transportadora>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(transportadoraService.buscarPorNome(nome));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Transportadora>> buscarPorTipo(@PathVariable Integer tipo) {
        return ResponseEntity.ok(transportadoraService.buscarPorTipo(tipo));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Transportadora transportadora) {
        try {
            return ResponseEntity.ok(transportadoraService.salvar(transportadora));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Transportadora transportadora) {
        try {
            return ResponseEntity.ok(transportadoraService.atualizar(id, transportadora));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(transportadoraService.inativar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            transportadoraService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
