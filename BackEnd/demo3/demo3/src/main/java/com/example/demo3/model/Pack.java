package com.example.demo3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import java.util.Set;

@Entity
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double prixTotal;
    private Double reduction;
    private String titre;
    private String description;

    @ManyToMany(mappedBy = "packs")
    private Set<Utilisateur> utilisateurs;

    // Getters et Setters pour utilisateurs et autres champs
}

