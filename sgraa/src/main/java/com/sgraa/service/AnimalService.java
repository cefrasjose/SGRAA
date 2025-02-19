package com.sgraa.service;

import com.sgraa.model.Animal;
import com.sgraa.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AnimalService {
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    public Animal salvar(Animal animal) {
        return animalRepository.save(animal);
    }
}
