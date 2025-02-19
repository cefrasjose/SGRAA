package com.sgraa.service;

import com.sgraa.model.Estoque;
import com.sgraa.repository.EstoqueRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class EstoqueService {
    private final EstoqueRepository estoqueRepository;

    public EstoqueService(EstoqueRepository estoqueRepository) {
        this.estoqueRepository = estoqueRepository;
    }

    // Método para consumir itens do estoque
    public boolean consumirItem(String item, int quantidade) {
        Optional<Estoque> estoqueOpt = estoqueRepository.findByItem(item);

        if (estoqueOpt.isEmpty() || estoqueOpt.get().getQuantidade() < quantidade) {
            return false; //Não há itens suficientes no estoque
        }

        Estoque estoque = estoqueOpt.get();
        estoque.setQuantidade(estoque.getQuantidade() - quantidade);
        estoqueRepository.save(estoque);
        return true; //Item consumido com sucesso
    }
}
