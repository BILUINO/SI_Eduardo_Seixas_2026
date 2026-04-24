package com.praticapro.sieduardoseixas2026.modalidadenfe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/modalidade-nfe")
@CrossOrigin(origins = "*")
public class ModalidadeNfeController {

    @Autowired
    private ModalidadeNfeService modalidadeNfeService;

    @GetMapping
    public List<ModalidadeNfe> listarTodos() {
        return modalidadeNfeService.listarTodos();
    }

    @GetMapping("/ativos")
    public List<ModalidadeNfe> listarAtivos() {
        return modalidadeNfeService.listarAtivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ModalidadeNfe> buscarPorId(@PathVariable Long id) {
        return modalidadeNfeService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ModalidadeNfe salvar(@RequestBody ModalidadeNfe modalidade) {
        return modalidadeNfeService.salvar(modalidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ModalidadeNfe> atualizar(@PathVariable Long id, @RequestBody ModalidadeNfe modalidade) {
        try {
            return ResponseEntity.ok(modalidadeNfeService.atualizar(id, modalidade));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        modalidadeNfeService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
