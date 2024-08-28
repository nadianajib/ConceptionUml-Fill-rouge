package com.example.demo3.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pack {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double prixTotal;
    private Double reduction;

    // Assurez-vous que l'importation de Service est correcte
    @OneToMany
    private List<Service> services;  // Utilise la classe com.example.demo3.model.Service
}
