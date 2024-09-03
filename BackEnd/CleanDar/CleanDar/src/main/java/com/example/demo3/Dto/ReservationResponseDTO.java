package com.example.demo3.Dto;

import lombok.Data;

@Data
public class ReservationResponseDTO {
    private Long id;
    private String dateDebut;
    private String heureDebut;
    private String typeService;
    private Long utilisateurId;
}


