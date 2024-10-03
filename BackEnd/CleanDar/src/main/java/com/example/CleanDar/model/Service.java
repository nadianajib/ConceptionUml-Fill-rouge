package com.example.CleanDar.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private Double prix; // Assurez-vous que ce champ est bien écrit
    private String image;

    @Enumerated(EnumType.STRING)
    private TypeService typeService;
}
