package com.sgraa.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Pretendente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String contato;
    private String endereco;
}
