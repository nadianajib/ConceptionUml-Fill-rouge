package com.example.demo3.Dto;

import java.util.Set;

public class PackDto {
    private Long id;
    private Double prixTotal;
    private Double reduction;
    private String titre;
    private String description;
    private Set<Long> utilisateurIds; // IDs des utilisateurs associés

    // Getters et Setters
}
