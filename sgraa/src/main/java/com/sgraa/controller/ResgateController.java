package com.sgraa.controller;

import com.sgraa.model.Resgate;
import com.sgraa.service.ResgateService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resgates")
public class ResgateController {
    private final ResgateService service;

    public ResgateController(ResgateService service) {
        this.service = service;
    }

    @GetMapping
    public List<Resgate> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Resgate criar(@RequestBody Resgate resgate) {
        return service.salvar(resgate);
    }
}
