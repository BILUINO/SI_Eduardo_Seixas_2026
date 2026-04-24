package com.praticapro.sieduardoseixas2026.contareceber;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/conta-receber")
@CrossOrigin(origins = "*")
public class ContaReceberController {

    @Autowired
    private ContaReceberService contaReceberService;

    @GetMapping
    public ResponseEntity<List<ContaReceber>> listarTodos() {
        return ResponseEntity.ok(contaReceberService.listarTodos());
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<ContaReceber>> listarAbertas() {
        return ResponseEntity.ok(contaReceberService.listarAbertas());
    }

    @GetMapping("/vencidas")
    public ResponseEntity<List<ContaReceber>> listarVencidas() {
        return ResponseEntity.ok(contaReceberService.listarVencidas());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ContaReceber>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(contaReceberService.listarPorStatus(status.toUpperCase()));
    }

    @GetMapping("/cliente/{clienteId}")
    public ResponseEntity<List<ContaReceber>> listarPorCliente(@PathVariable Long clienteId) {
        return ResponseEntity.ok(contaReceberService.listarPorCliente(clienteId));
    }

    @GetMapping("/nota-saida/{notaSaidaId}")
    public ResponseEntity<List<ContaReceber>> listarPorNotaSaida(@PathVariable Long notaSaidaId) {
        return ResponseEntity.ok(contaReceberService.listarPorNotaSaida(notaSaidaId));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<ContaReceber>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(contaReceberService.listarPorPeriodo(inicio, fim));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaReceber> buscarPorId(@PathVariable Long id) {
        return contaReceberService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ContaReceber conta) {
        try {
            return ResponseEntity.ok(contaReceberService.salvar(conta));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ContaReceber conta) {
        try {
            return ResponseEntity.ok(contaReceberService.atualizar(id, conta));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/receber")
    public ResponseEntity<?> receber(@PathVariable Long id, @RequestBody(required = false) Map<String, Object> body) {
        try {
            BigDecimal valorRecebido = null;
            LocalDate dataRecebimento = null;
            BigDecimal juros = null;
            BigDecimal multa = null;
            BigDecimal desconto = null;

            if (body != null) {
                if (body.get("valorRecebido") != null) valorRecebido = new BigDecimal(body.get("valorRecebido").toString());
                if (body.get("dataRecebimento") != null) dataRecebimento = LocalDate.parse(body.get("dataRecebimento").toString());
                if (body.get("valorJuros") != null) juros = new BigDecimal(body.get("valorJuros").toString());
                if (body.get("valorMulta") != null) multa = new BigDecimal(body.get("valorMulta").toString());
                if (body.get("valorDesconto") != null) desconto = new BigDecimal(body.get("valorDesconto").toString());
            }
            return ResponseEntity.ok(contaReceberService.receber(id, valorRecebido, dataRecebimento, juros, multa, desconto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/estornar")
    public ResponseEntity<?> estornar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contaReceberService.estornar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contaReceberService.cancelar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            contaReceberService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
