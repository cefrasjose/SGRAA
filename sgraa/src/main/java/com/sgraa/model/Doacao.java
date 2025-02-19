package com.sgraa.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Doacao {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Doador doador;

    private String item;
    private Integer quantidade;
    private LocalDate dataDoacao;
}
