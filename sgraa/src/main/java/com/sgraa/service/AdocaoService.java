package com.sgraa.service;

import com.sgraa.model.Adocao;
import com.sgraa.repository.AdocaoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdocaoService {
    private final AdocaoRepository repository;

    public AdocaoService(AdocaoRepository repository) {
        this.repository = repository;
    }

    public List<Adocao> listarTodos() {
        return repository.findAll();
    }

    public Adocao salvar(Adocao adocao) {
        return repository.save(adocao);
    }
}
