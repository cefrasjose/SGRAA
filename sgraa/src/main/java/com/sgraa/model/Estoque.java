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
    private Integer quantidade;
}
