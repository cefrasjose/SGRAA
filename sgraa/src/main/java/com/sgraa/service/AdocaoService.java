package com.sgraa.service;

import com.sgraa.model.Adocao;
import com.sgraa.repository.AdocaoRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdocaoService {
    private final AdocaoRepository adocaoRepository;

    public AdocaoService(AdocaoRepository adocaoRepository) {
        this.adocaoRepository = adocaoRepository;
    }

    //Método para listar todas as adoções
    public List<Adocao> listarTodos() {
        return adocaoRepository.findAll();
    }

    //Método para salvar uma adoção
    public Adocao salvar(Adocao adocao) {
        return adocaoRepository.save(adocao);
    }
}
