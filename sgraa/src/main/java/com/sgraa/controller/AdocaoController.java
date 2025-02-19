package com.sgraa.controller;

import com.sgraa.model.Adocao;
import com.sgraa.service.AdocaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/adocoes")
public class AdocaoController {
    private final AdocaoService service;

    public AdocaoController(AdocaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Adocao> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Adocao criar(@RequestBody Adocao adocao) {
        return service.salvar(adocao);
    }
}
