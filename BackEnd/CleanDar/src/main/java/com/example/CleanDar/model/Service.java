package com.example.CleanDar.model;

import jakarta.persistence.*;


import lombok.Data;


@Data
@Entity
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String description;
    private Double prix;
    private String image;

    @Enumerated(EnumType.STRING)
    private TypeService typeService;
}

