package com.sgraa.controller;

import com.sgraa.model.Estoque;
import com.sgraa.service.EstoqueService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/estoque")
public class EstoqueController {
    private final EstoqueService service;

    public EstoqueController(EstoqueService service) {
        this.service = service;
    }

    @GetMapping
    public List<Estoque> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Estoque criar(@RequestBody Estoque estoque) {
        return service.salvar(estoque);
    }
}
