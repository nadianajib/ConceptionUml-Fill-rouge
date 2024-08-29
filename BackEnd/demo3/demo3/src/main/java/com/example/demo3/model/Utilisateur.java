package com.example.demo3.model;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;

import java.util.Set;


@Entity
public class Utilisateur extends Personne {

    @ManyToMany
    @JoinTable(
            name = "utilisateur_pack",
            joinColumns = @JoinColumn(name = "utilisateur_id"),
            inverseJoinColumns = @JoinColumn(name = "pack_id")
    )
    private Set<Pack> packs;

    public void createReservation() {
        // Logic to create a reservation
    }

    public void consulterReservation() {
        // Logic to consult reservations
    }

    public void modifierReservation() {
        // Logic to modify reservations
    }

    public void annulerReservation() {
        // Logic to cancel reservations
    }

    // Getters et Setters pour packs
}
