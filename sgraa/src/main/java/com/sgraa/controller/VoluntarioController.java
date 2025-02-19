package com.sgraa.controller;

import com.sgraa.model.Voluntario;
import com.sgraa.service.VoluntarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voluntarios")
public class VoluntarioController {
    private final VoluntarioService service;

    public VoluntarioController(VoluntarioService service) {
        this.service = service;
    }

    @GetMapping
    public List<Voluntario> listar() {
        return service.listarTodos();
    }

    @PostMapping
    public Voluntario criar(@RequestBody Voluntario voluntario) {
        return service.salvar(voluntario);
    }
}
