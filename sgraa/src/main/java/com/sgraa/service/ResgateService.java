package com.sgraa.service;

import com.sgraa.model.Resgate;
import com.sgraa.repository.ResgateRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ResgateService {
    private final ResgateRepository repository;

    public ResgateService(ResgateRepository repository) {
        this.repository = repository;
    }

    public List<Resgate> listarTodos() {
        return repository.findAll();
    }

    public Resgate salvar(Resgate resgate) {
        return repository.save(resgate);
    }
}
