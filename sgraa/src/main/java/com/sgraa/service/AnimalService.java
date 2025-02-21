package com.sgraa.service;

import com.sgraa.exception.ResourceNotFoundException;
import com.sgraa.model.Animal;
import com.sgraa.repository.AnimalRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class AnimalService {
    private static final Logger logger = LoggerFactory.getLogger(AnimalService.class);
    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> listarTodos() {
        return animalRepository.findAll();
    }

    public Animal buscarPorId(Long id) {
        return animalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Animal não encontrado com ID: " + id));
    }

    public Animal salvar(Animal animal) {
        logger.info("Salvando novo animal: {}", animal);
        return animalRepository.save(animal);
    }
}
