package com.example.CleanDar.model;

import com.example.CleanDar.model.Utilisateur;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dateDebut;
    private String dateFin;
    private String image;
    @ManyToOne
    @JoinColumn(name = "utilisateur_id")
    private Utilisateur utilisateur;

    @ManyToOne
    @JoinColumn(name = "pack_id")
    private Pack pack;

}
