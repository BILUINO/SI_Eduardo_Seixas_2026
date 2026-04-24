package com.praticapro.sieduardoseixas2026.contapagar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/conta-pagar")
@CrossOrigin(origins = "*")
public class ContaPagarController {

    @Autowired
    private ContaPagarService contaPagarService;

    @GetMapping
    public ResponseEntity<List<ContaPagar>> listarTodos() {
        return ResponseEntity.ok(contaPagarService.listarTodos());
    }

    @GetMapping("/abertas")
    public ResponseEntity<List<ContaPagar>> listarAbertas() {
        return ResponseEntity.ok(contaPagarService.listarAbertas());
    }

    @GetMapping("/vencidas")
    public ResponseEntity<List<ContaPagar>> listarVencidas() {
        return ResponseEntity.ok(contaPagarService.listarVencidas());
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<ContaPagar>> listarPorStatus(@PathVariable String status) {
        return ResponseEntity.ok(contaPagarService.listarPorStatus(status.toUpperCase()));
    }

    @GetMapping("/fornecedor/{fornecedorId}")
    public ResponseEntity<List<ContaPagar>> listarPorFornecedor(@PathVariable Long fornecedorId) {
        return ResponseEntity.ok(contaPagarService.listarPorFornecedor(fornecedorId));
    }

    @GetMapping("/nota-entrada/{notaEntradaId}")
    public ResponseEntity<List<ContaPagar>> listarPorNotaEntrada(@PathVariable Long notaEntradaId) {
        return ResponseEntity.ok(contaPagarService.listarPorNotaEntrada(notaEntradaId));
    }

    @GetMapping("/periodo")
    public ResponseEntity<List<ContaPagar>> listarPorPeriodo(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate inicio,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate fim) {
        return ResponseEntity.ok(contaPagarService.listarPorPeriodo(inicio, fim));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ContaPagar> buscarPorId(@PathVariable Long id) {
        return contaPagarService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> criar(@RequestBody ContaPagar conta) {
        try {
            return ResponseEntity.ok(contaPagarService.salvar(conta));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody ContaPagar conta) {
        try {
            return ResponseEntity.ok(contaPagarService.atualizar(id, conta));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/pagar")
    public ResponseEntity<?> pagar(@PathVariable Long id, @RequestBody(required = false) Map<String, Object> body) {
        try {
            BigDecimal valorPago = null;
            LocalDate dataPagamento = null;
            BigDecimal juros = null;
            BigDecimal multa = null;
            BigDecimal desconto = null;

            if (body != null) {
                if (body.get("valorPago") != null) valorPago = new BigDecimal(body.get("valorPago").toString());
                if (body.get("dataPagamento") != null) dataPagamento = LocalDate.parse(body.get("dataPagamento").toString());
                if (body.get("valorJuros") != null) juros = new BigDecimal(body.get("valorJuros").toString());
                if (body.get("valorMulta") != null) multa = new BigDecimal(body.get("valorMulta").toString());
                if (body.get("valorDesconto") != null) desconto = new BigDecimal(body.get("valorDesconto").toString());
            }
            return ResponseEntity.ok(contaPagarService.pagar(id, valorPago, dataPagamento, juros, multa, desconto));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/estornar")
    public ResponseEntity<?> estornar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contaPagarService.estornar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PatchMapping("/{id}/cancelar")
    public ResponseEntity<?> cancelar(@PathVariable Long id) {
        try {
            return ResponseEntity.ok(contaPagarService.cancelar(id));
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletar(@PathVariable Long id) {
        try {
            contaPagarService.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
