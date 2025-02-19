package com.sgraa.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Resgate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate dataResgate;
    private String localidade;
    private String descricao;

    @ManyToMany
    @JoinTable(
            name = "rescue_animal",
            joinColumns = @JoinColumn(name = "rescue_id"),
            inverseJoinColumns = @JoinColumn(name = "animal_id")
    )
    private List<Animal> animais;
}
