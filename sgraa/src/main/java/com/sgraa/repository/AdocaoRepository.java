package com.sgraa.repository;

import com.sgraa.model.Adocao;
import com.sgraa.model.Animal;
import com.sgraa.model.Pretendente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdocaoRepository extends JpaRepository<Adocao, Long> {
    boolean existsByAnimalAndPretendente(Animal animal, Pretendente pretendente);
}
