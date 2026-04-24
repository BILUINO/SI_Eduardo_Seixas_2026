package com.praticapro.sieduardoseixas2026.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/funcao-funcionario")
@CrossOrigin(origins = "*")
public class FuncaoFuncionarioController {

    @Autowired
    private FuncaoFuncionarioService funcaoFuncionarioService;

    @GetMapping
    public List<FuncaoFuncionario> listarTodos() {
        return funcaoFuncionarioService.listarTodos();
    }

    @GetMapping("/ativos")
    public List<FuncaoFuncionario> listarAtivos() {
        return funcaoFuncionarioService.listarAtivos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FuncaoFuncionario> buscarPorId(@PathVariable Long id) {
        return funcaoFuncionarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FuncaoFuncionario salvar(@RequestBody FuncaoFuncionario funcao) {
        return funcaoFuncionarioService.salvar(funcao);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuncaoFuncionario> atualizar(@PathVariable Long id, @RequestBody FuncaoFuncionario funcao) {
        try {
            return ResponseEntity.ok(funcaoFuncionarioService.atualizar(id, funcao));
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        funcaoFuncionarioService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
