package com.example.demo3.Dto;
import lombok.Data;

@Data
public class ReservationDto {
    private String dateDebut;
    private String heureDebut;
    private String typeService;
    private Long utilisateurId;
}
