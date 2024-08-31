package com.example.demo3.Dto;

import lombok.Data;

@Data
public class ReservationDto {
    private Long id;
    private String dateDebut;
    private String dateFin;
    private Long utilisateurId; // Identifiant pour Utilisateur
    private Long packId; // Identifiant pour Pack
}
