package com.sgraa.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Doador {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String contato;
    private String tipo; // Pessoa Física ou Instituição
}
