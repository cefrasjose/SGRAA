package com.sgraa.service;

import com.sgraa.model.Pretendente;
import com.sgraa.repository.PretendenteRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class PretendenteService {
    private final PretendenteRepository repository;

    public PretendenteService(PretendenteRepository repository) {
        this.repository = repository;
    }

    public List<Pretendente> listarTodos() {
        return repository.findAll();
    }

    public Pretendente salvar(Pretendente pretendente) {
        return repository.save(pretendente);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
