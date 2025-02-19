package com.sgraa.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Adocao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Animal animal;

    @ManyToOne
    private Pretendente pretendente;

    private LocalDate dataAdocao;
}
