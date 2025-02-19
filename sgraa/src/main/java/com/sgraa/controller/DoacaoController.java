package com.sgraa.controller;

import com.sgraa.model.Doacao;
import com.sgraa.service.DoacaoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doacoes")
public class DoacaoController {
    private final DoacaoService service;

    public DoacaoController(DoacaoService service) {
        this.service = service;
    }

    @GetMapping
    public List<Doacao> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Doacao criar(@RequestBody Doacao doacao) {
        return service.salvar(doacao);
    }
}
