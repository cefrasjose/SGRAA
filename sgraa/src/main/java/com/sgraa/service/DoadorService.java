package com.sgraa.service;

import com.sgraa.model.Doador;
import com.sgraa.repository.DoadorRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoadorService {
    private final DoadorRepository repository;

    public DoadorService(DoadorRepository repository) {
        this.repository = repository;
    }

    public List<Doador> listarTodos() {
        return repository.findAll();
    }

    public Doador salvar(Doador doador) {
        return repository.save(doador);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
