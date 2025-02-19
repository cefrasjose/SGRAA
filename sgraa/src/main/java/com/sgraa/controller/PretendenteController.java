package com.sgraa.controller;

import com.sgraa.model.Pretendente;
import com.sgraa.service.PretendenteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pretendentes")
public class PretendenteController {
    private final PretendenteService service;

    public PretendenteController(PretendenteService service) {
        this.service = service;
    }

    @GetMapping
    public List<Pretendente> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Pretendente criar(@RequestBody Pretendente pretendente) {
        return service.salvar(pretendente);
    }
}
