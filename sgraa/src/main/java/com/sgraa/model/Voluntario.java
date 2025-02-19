package com.sgraa.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Voluntario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String contato;

    @Enumerated(EnumType.STRING)
    private FuncaoVoluntario funcao;
}
