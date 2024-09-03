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

    private Double prixTotal;
    private Double reduction;
    private String titre;
    private String description;

    @OneToMany(mappedBy = "pack")
    private List<Reservation> reservations;

}
