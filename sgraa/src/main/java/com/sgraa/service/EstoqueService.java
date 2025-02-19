package com.sgraa.service;

import com.sgraa.model.Estoque;
import com.sgraa.repository.EstoqueRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class EstoqueService {
    private final EstoqueRepository repository;

    public EstoqueService(EstoqueRepository repository) {
        this.repository = repository;
    }

    public List<Estoque> listarTodos() {
        return repository.findAll();
    }

    public Estoque salvar(Estoque estoque) {
        return repository.save(estoque);
    }
}
