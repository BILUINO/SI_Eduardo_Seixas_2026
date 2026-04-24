package com.praticapro.sieduardoseixas2026.funcionario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/funcionario")
@CrossOrigin(origins = "*")
public class FuncionarioController {

    @Autowired
    private FuncionarioService funcionarioService;

    @GetMapping
    public ResponseEntity<List<Funcionario>> listarTodos() {
        return ResponseEntity.ok(funcionarioService.listarTodos());
    }

    @GetMapping("/ativos")
    public ResponseEntity<List<Funcionario>> listarAtivos() {
        return ResponseEntity.ok(funcionarioService.listarAtivos());
    }

    @GetMapping("/empregados")
    public ResponseEntity<List<Funcionario>> listarEmpregados() {
        return ResponseEntity.ok(funcionarioService.listarEmpregados());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Funcionario> buscarPorId(@PathVariable Long id) {
        return funcionarioService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Funcionario> buscarPorCpf(@PathVariable String cpf) {
        return funcionarioService.buscarPorCpf(cpf)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/matricula/{matricula}")
    public ResponseEntity<Funcionario> buscarPorMatricula(@PathVariable String matricula) {
        return funcionarioService.buscarPorMatricula(matricula)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/buscar")
    public ResponseEntity<List<Funcionario>> buscarPorNome(@RequestParam String nome) {
        return ResponseEntity.ok(funcionarioService.buscarPorNome(nome));
    }

    @GetMapping("/funcao/{funcaoId}")
    public ResponseEntity<List<Funcionario>> buscarPorFuncao(@PathVariable Long funcaoId) {
        return ResponseEntity.ok(funcionarioService.buscarPorFuncao(funcaoId));
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody Funcionario funcionario) {
        try {
            return ResponseEntity.ok(funcionarioService.salvar(funcionario));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Funcionario funcionario) {
        try {
            return ResponseEntity.ok(funcionarioService.atualizar(id, funcionario));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/inativar")
    public ResponseEntity<?> inativar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(funcionarioService.inativar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/demitir")
    public ResponseEntity<?> demitir(@PathVariable Long id, @RequestBody(required = false) Map<String, String> body) {
        try {
            LocalDate dataDemissao = null;
            if (body != null && body.get("dataDemissao") != null) {
                dataDemissao = LocalDate.parse(body.get("dataDemissao"));
            }
            return ResponseEntity.ok(funcionarioService.demitir(id, dataDemissao));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            funcionarioService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
