package com.example.CleanDar.Dto;

import com.example.CleanDar.model.TypeService;
import lombok.Data;

@Data

public class ServiceNettoyageDto {
    private Long id;
    private String nom;
    private String description;
    private Double prix;
    private String image;

    private Long pack_id;
    private TypeService typeService;

    // Getters et Setters
}


