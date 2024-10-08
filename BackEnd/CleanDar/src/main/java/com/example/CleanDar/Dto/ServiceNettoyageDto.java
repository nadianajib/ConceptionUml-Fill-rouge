package com.example.CleanDar.Dto;

import lombok.Data;

@Data

public class ServiceNettoyageDto {
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private String image;

    private String typeService;

    // Getters et Setters
}


