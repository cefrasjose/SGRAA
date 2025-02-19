package com.sgraa.repository;

import com.sgraa.model.Doador;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DoadorRepository extends JpaRepository<Doador, Long> {
}
