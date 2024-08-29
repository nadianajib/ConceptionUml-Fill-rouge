package com.example.demo3.model;

import jakarta.persistence.*;


@Entity
public class Service {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nom;
    private String description;
    private Double prix;

    @Enumerated(EnumType.STRING)
    private TypeService typeService;

    // Getters et Setters
}
