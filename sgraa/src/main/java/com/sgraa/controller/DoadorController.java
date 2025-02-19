package com.sgraa.controller;

import com.sgraa.model.Doador;
import com.sgraa.service.DoadorService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/doadores")
public class DoadorController {
    private final DoadorService service;

    public DoadorController(DoadorService service) {
        this.service = service;
    }

    @GetMapping
    public List<Doador> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Doador criar(@RequestBody Doador doador) {
        return service.salvar(doador);
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
