package com.sgraa.service;

import com.sgraa.model.Animal;
import com.sgraa.model.Resgate;
import com.sgraa.repository.AnimalRepository;
import com.sgraa.repository.ResgateRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ResgateService {
    private final ResgateRepository resgateRepository;
    private final AnimalRepository animalRepository;

    public ResgateService(ResgateRepository resgateRepository, AnimalRepository animalRepository) {
        this.resgateRepository = resgateRepository;
        this.animalRepository = animalRepository;
    }

    public List<Resgate> listarTodos() {
        return resgateRepository.findAll();
    }

    public Resgate salvar(Resgate resgate) {
        // Buscar os animais no banco de dados pelos IDs informados
        List<Animal> animaisValidos = resgate.getAnimais().stream()
                .map(animal -> animalRepository.findById(animal.getId()))
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());

        // Verifica se todos os IDs informados realmente existem no banco
        if (animaisValidos.isEmpty() || animaisValidos.size() != resgate.getAnimais().size()) {
            throw new RuntimeException("Um ou mais animais não existem no banco de dados.");
        }

        // Associar os animais validados ao resgate antes de salvar
        resgate.setAnimais(animaisValidos);

        return resgateRepository.save(resgate);
    }
}
