package com.example.demo3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Utilisateur utilisateur;

    @ManyToOne
    private Pack pack;

    private LocalDate date; // Date de la réservation
    private LocalTime heure; // Heure de la réservation

    // Getters et Setters (générés par Lombok via @Data)
}
