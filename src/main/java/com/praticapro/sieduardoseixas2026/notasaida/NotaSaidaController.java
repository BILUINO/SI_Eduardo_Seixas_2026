package com.praticapro.sieduardoseixas2026.notasaida;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/nota-saida")
@CrossOrigin(origins = "*")
public class NotaSaidaController {

    @Autowired
    private NotaSaidaService notaSaidaService;

    @GetMapping
    public ResponseEntity<List<NotaSaida>> listarTodos() {
        return ResponseEntity.ok(notaSaidaService.listarTodos());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<NotaSaida>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(notaSaidaService.listarPorStatus(status.toUpperCase()));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<NotaSaida>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(notaSaidaService.listarPorCliente(clienteId));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<NotaSaida>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(notaSaidaService.listarPorPeriodo(inicio, fim));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaSaida> buscarPorId(@PathVariable Long id) {
        return notaSaidaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody NotaSaida nota) {
        try {
            return ResponseEntity.ok(notaSaidaService.salvar(nota));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody NotaSaida nota) {
        try {
            return ResponseEntity.ok(notaSaidaService.atualizar(id, nota));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<?> confirmar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(notaSaidaService.confirmar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(notaSaidaService.cancelar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            notaSaidaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
