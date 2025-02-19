package com.sgraa.controller;

import com.sgraa.model.Doacao;
import com.sgraa.service.DoacaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {
    private final DoacaoService doacaoService;

    public DoacaoController(DoacaoService doacaoService) {
        this.doacaoService = doacaoService;
    }

    // ✅ Endpoint para listar todas as doações
    @GetMapping
    public ResponseEntity<List<Doacao>> listarTodos() {
        return ResponseEntity.ok(doacaoService.listarTodos());
    }

    // ✅ Endpoint para salvar uma nova doação
    @PostMapping
    public ResponseEntity<Doacao> salvar(@RequestBody Doacao doacao) {
        return ResponseEntity.ok(doacaoService.salvar(doacao));
    }
}
