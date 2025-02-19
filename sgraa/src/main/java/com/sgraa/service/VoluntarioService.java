package com.sgraa.service;

import com.sgraa.model.Voluntario;
import com.sgraa.repository.VoluntarioRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class VoluntarioService {
    private final VoluntarioRepository repository;

    public VoluntarioService(VoluntarioRepository repository) {
        this.repository = repository;
    }

    public List<Voluntario> listarTodos() {
        return repository.findAll();
    }

    public Voluntario salvar(Voluntario voluntario) {
        return repository.save(voluntario);
    }
}
