package com.praticapro.sieduardoseixas2026.notaentrada;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/nota-entrada")
@CrossOrigin(origins = "*")
public class NotaEntradaController {

    @Autowired
    private NotaEntradaService notaEntradaService;

    @GetMapping
    public ResponseEntity<List<NotaEntrada>> listarTodos() {
        return ResponseEntity.ok(notaEntradaService.listarTodos());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<NotaEntrada>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(notaEntradaService.listarPorStatus(status.toUpperCase()));
    }

    @GetMapping("/fornecedor/{fornecedorId}")
    public ResponseEntity<List<NotaEntrada>> listarPorFornecedor(@PathVariable Long fornecedorId) {
        return ResponseEntity.ok(notaEntradaService.listarPorFornecedor(fornecedorId));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<NotaEntrada>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(notaEntradaService.listarPorPeriodo(inicio, fim));
    }

    @GetMapping("/{id}")
    public ResponseEntity<NotaEntrada> buscarPorId(@PathVariable Long id) {
        return notaEntradaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody NotaEntrada nota) {
        try {
            return ResponseEntity.ok(notaEntradaService.salvar(nota));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody NotaEntrada nota) {
        try {
            return ResponseEntity.ok(notaEntradaService.atualizar(id, nota));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/confirmar")
    public ResponseEntity<?> confirmar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(notaEntradaService.confirmar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(notaEntradaService.cancelar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            notaEntradaService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
