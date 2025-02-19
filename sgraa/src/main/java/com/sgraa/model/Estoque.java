package com.sgraa.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String item;
    private int quantidade;

    //Adicionando um construtor com parâmetros
    public Estoque(String item, int quantidade) {
        this.item = item;
        this.quantidade = quantidade;
    }

    //Construtor padrão sem argumentos (necessário para JPA)
    public Estoque() {
    }
}
