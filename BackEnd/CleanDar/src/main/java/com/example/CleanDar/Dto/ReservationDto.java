package com.example.CleanDar.Dto;

import lombok.Data;

@Data
public class ReservationDto {
    private Long id;
    private String dateDebut;
    private String dateFin;
    private String status;
    private Long utilisateurId; // Identifiant pour Utilisateur
    private Long packId; // Identifiant pour Pack

}
