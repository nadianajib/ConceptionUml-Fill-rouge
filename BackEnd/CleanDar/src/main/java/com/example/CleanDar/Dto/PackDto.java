package com.example.CleanDar.Dto;

import lombok.Data;

@Data
public class PackDto {
    private Long id; // Ajouter l'id pour identifier le pack
    private Double prixTotal;
    private Double reduction;
    private String titre;
    private String description;
    private String image;
}
