package com.sgraa.controller;

import com.sgraa.model.Adocao;
import com.sgraa.service.AdocaoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adocoes")
public class AdocaoController {
    private final AdocaoService adocaoService;

    public AdocaoController(AdocaoService adocaoService) {
        this.adocaoService = adocaoService;
    }

    // ✅ Endpoint para listar todas as adoções
    @GetMapping
    public ResponseEntity<List<Adocao>> listarTodos() {
        return ResponseEntity.ok(adocaoService.listarTodos());
    }

    // ✅ Endpoint para salvar uma adoção
    @PostMapping
    public ResponseEntity<Adocao> salvar(@RequestBody Adocao adocao) {
        return ResponseEntity.ok(adocaoService.salvar(adocao));
    }
}
