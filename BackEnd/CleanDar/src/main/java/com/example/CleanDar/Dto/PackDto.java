package com.example.CleanDar.Dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
@Data
public class PackDto {
    private Long id;
    private Double prixTotal;
    private Double reduction;
    private String titre;
    private String description;
    private String image;
    private List<Long> servicesIds = new ArrayList<>();
//    private Set<Long> utilisateurId; // IDs des utilisateurs associés

    // Getters et Setters
}
