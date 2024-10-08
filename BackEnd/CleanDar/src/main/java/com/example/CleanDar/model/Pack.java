package com.example.CleanDar.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Pack {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Double prixTotal; // Prix après réduction
    private Double reduction; // Pourcentage de réduction
    private String titre;
    private String description;
    private String image;

    @OneToMany(mappedBy = "pack")
    private List<Reservation> reservations;

    @OneToMany(mappedBy = "pack")
    private List<ServiceNettoyage> serviceNettoyages;
}
