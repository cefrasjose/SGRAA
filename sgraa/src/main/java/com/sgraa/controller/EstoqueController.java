package com.sgraa.controller;

import com.sgraa.service.EstoqueService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {
    private final EstoqueService estoqueService;

    public EstoqueController(EstoqueService estoqueService) {
        this.estoqueService = estoqueService;
    }

    // ✅ Endpoint para consumir item do estoque
    @PostMapping("/consumir")
    public ResponseEntity<String> consumirItem(@RequestBody Map<String, Object> request) {
        String item = (String) request.get("item");
        int quantidade = (int) request.get("quantidade");

        boolean sucesso = estoqueService.consumirItem(item, quantidade);
        if (!sucesso) {
            return ResponseEntity.badRequest().body("Estoque insuficiente para o item: " + item);
        }
        return ResponseEntity.ok("Item consumido com sucesso!");
    }
}
