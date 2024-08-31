package com.example.demo3.Dao;

import com.example.demo3.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Méthode pour trouver les réservations par utilisateur
    List<Reservation> findByUtilisateurId(Long utilisateurId);
}
