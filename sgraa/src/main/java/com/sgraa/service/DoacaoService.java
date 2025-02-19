package com.sgraa.service;

import com.sgraa.model.Doacao;
import com.sgraa.repository.DoacaoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoacaoService {
    private final DoacaoRepository doacaoRepository;

    public DoacaoService(DoacaoRepository doacaoRepository) {
        this.doacaoRepository = doacaoRepository;
    }

    // ✅ Método para listar todas as doações
    public List<Doacao> listarTodos() {
        return doacaoRepository.findAll();
    }

    // ✅ Método para salvar uma nova doação
    public Doacao salvar(Doacao doacao) {
        return doacaoRepository.save(doacao);
    }
}
