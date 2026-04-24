package com.praticapro.sieduardoseixas2026.condicaopagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/condicao-pagamento")
@CrossOrigin(origins = "*")
public class CondicaoPagamentoController {

    @Autowired
    private CondicaoPagamentoService condicaoPagamentoService;

    @GetMapping
    public ResponseEntity<List<CondicaoPagamento>> listarTodos() {
        return ResponseEntity.ok(condicaoPagamentoService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<CondicaoPagamento>> listarAtivos() {
        return ResponseEntity.ok(condicaoPagamentoService.listarAtivos());
    }

    @GetMapping("/a-vista")
    public ResponseEntity<List<CondicaoPagamento>> listarAVista() {
        return ResponseEntity.ok(condicaoPagamentoService.listarAVista());
    }

    @GetMapping("/{id}")
    public ResponseEntity<CondicaoPagamento> buscarPorId(@PathVariable Long id) {
        return condicaoPagamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<CondicaoPagamento>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(condicaoPagamentoService.buscarPorNome(nome));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody CondicaoPagamento condicao) {
        try {
            return ResponseEntity.ok(condicaoPagamentoService.salvar(condicao));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody CondicaoPagamento condicao) {
        try {
            return ResponseEntity.ok(condicaoPagamentoService.atualizar(id, condicao));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(condicaoPagamentoService.inativar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            condicaoPagamentoService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
