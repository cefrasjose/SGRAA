package com.sgraa.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Data
public class Animal {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String especie;
    private String raca;
    private Integer idade;
    private String sexo;
    private String porte;
    private String cor;

    @Enumerated(EnumType.STRING)
    private StatusAdocao status;

    private LocalDate dataChegada;
    private LocalDate dataSaida;
}
