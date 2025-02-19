package com.sgraa.service;

import com.sgraa.model.Doacao;
import com.sgraa.repository.DoacaoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DoacaoService {
    private final DoacaoRepository repository;

    public DoacaoService(DoacaoRepository repository) {
        this.repository = repository;
    }

    public List<Doacao> listarTodos() {
        return repository.findAll();
    }

    public Doacao salvar(Doacao doacao) {
        return repository.save(doacao);
    }
}
