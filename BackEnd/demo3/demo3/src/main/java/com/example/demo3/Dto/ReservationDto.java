package com.example.demo3.Dto;

import lombok.Data;

@Data
public class ReservationDto {
    private Long id; // Utilisé uniquement pour la consultation
    private String dateDebut;
    private String dateFin;
    private Long utilisateurId; // Utilisé pour la création
    private Long packId; // Utilisé pour la création
    private String utilisateurNom; // Utilisé pour la consultation
    private String packTitre; // Utilisé pour la consultation
}
