package com.sgraa.service;

import com.sgraa.exception.BusinessRuleException;
import com.sgraa.exception.ResourceNotFoundException;
import com.sgraa.model.Adocao;
import com.sgraa.model.Animal;
import com.sgraa.model.Pretendente;
import com.sgraa.model.StatusAdocao;
import com.sgraa.repository.AdocaoRepository;
import com.sgraa.repository.AnimalRepository;
import com.sgraa.repository.PretendenteRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdocaoService {
    private final AdocaoRepository adocaoRepository;
    private final AnimalRepository animalRepository;
    private final PretendenteRepository pretendenteRepository;

    public AdocaoService(AdocaoRepository adocaoRepository, AnimalRepository animalRepository, PretendenteRepository pretendenteRepository) {
        this.adocaoRepository = adocaoRepository;
        this.animalRepository = animalRepository;
        this.pretendenteRepository = pretendenteRepository;
    }

    //Método para listar todas as adoções
    public List<Adocao> listarTodos() {
        return adocaoRepository.findAll();
    }

    //Método para salvar uma adoção
    public Adocao salvar(Adocao adocao) {
        return adocaoRepository.save(adocao);
    }

    public Adocao registrarAdocao(Long animalId, Long pretendenteId) {
        Animal animal = animalRepository.findById(animalId)
                .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado com ID: " + animalId));

        Pretendente pretendente = pretendenteRepository.findById(pretendenteId)
                .orElseThrow(() -> new ResourceNotFoundException("Pretendente não encontrado com ID: " + pretendenteId));

        if (adocaoRepository.existsByAnimalAndPretendente(animal, pretendente)) {
            throw new BusinessRuleException("Este pretendente já adotou este animal.");
        }

        if (animal.getStatus() == StatusAdocao.ADOTADO) {
            throw new BusinessRuleException("Este animal já foi adotado.");
        }

        Adocao adocao = new Adocao();
        adocao.setAnimal(animal);
        adocao.setPretendente(pretendente);
        adocao.setDataAdocao(java.time.LocalDate.now());

        animal.setStatus(StatusAdocao.ADOTADO);
        animalRepository.save(animal);

        return adocaoRepository.save(adocao);
    }
}