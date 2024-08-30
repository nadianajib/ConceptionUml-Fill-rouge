package com.example.demo3.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
public class Utilisateur extends Personne {

    @OneToMany(mappedBy = "utilisateur")
    private List<Reservation> reservations;


}
