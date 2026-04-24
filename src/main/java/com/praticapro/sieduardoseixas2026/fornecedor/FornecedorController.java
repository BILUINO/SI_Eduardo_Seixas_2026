package com.praticapro.sieduardoseixas2026.fornecedor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/fornecedor")
@CrossOrigin(origins = "*")
public class FornecedorController {

    @Autowired
    private FornecedorService fornecedorService;

    @GetMapping
    public ResponseEntity<List<Fornecedor>> listarTodos() {
        return ResponseEntity.ok(fornecedorService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Fornecedor>> listarAtivos() {
        return ResponseEntity.ok(fornecedorService.listarAtivos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Fornecedor> buscarPorId(@PathVariable Long id) {
        return fornecedorService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf-cnpj/{cpfCnpj}")
    public ResponseEntity<Fornecedor> buscarPorCpfCnpj(@PathVariable String cpfCnpj) {
        return fornecedorService.buscarPorCpfCnpj(cpfCnpj)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Fornecedor>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(fornecedorService.buscarPorNome(nome));
    }

    @GetMapping("/tipo/{tipo}")
    public ResponseEntity<List<Fornecedor>> buscarPorTipo(@PathVariable Integer tipo) {
        return ResponseEntity.ok(fornecedorService.buscarPorTipo(tipo));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Fornecedor fornecedor) {
        try {
            return ResponseEntity.ok(fornecedorService.salvar(fornecedor));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Fornecedor fornecedor) {
        try {
            return ResponseEntity.ok(fornecedorService.atualizar(id, fornecedor));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(fornecedorService.inativar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            fornecedorService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
