package com.praticapro.sieduardoseixas2026.formapagamento;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/forma-pagamento")
@CrossOrigin(origins = "*")
public class FormaPagamentoController {

    @Autowired
    private FormaPagamentoService formaPagamentoService;

    @GetMapping
    public List<FormaPagamento> listarTodos() {
        return formaPagamentoService.listarTodos();
    }

    @GetMapping("/ativos")
    public List<FormaPagamento> listarAtivos() {
        return formaPagamentoService.listarAtivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FormaPagamento> buscarPorId(@PathVariable Long id) {
        return formaPagamentoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FormaPagamento salvar(@RequestBody FormaPagamento formaPagamento) {
        return formaPagamentoService.salvar(formaPagamento);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FormaPagamento> atualizar(@PathVariable Long id, @RequestBody FormaPagamento formaPagamento) {
        try {
            return ResponseEntity.ok(formaPagamentoService.atualizar(id, formaPagamento));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        formaPagamentoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
