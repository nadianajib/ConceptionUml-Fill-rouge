package com.example.CleanDar.Dao;



import com.example.CleanDar.model.Reservation;
import com.example.CleanDar.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
    // Méthode pour trouver les réservations par utilisateur
    List<Reservation> findByUtilisateurId(Long utilisateurId);

    List<Reservation> findByUtilisateur(Utilisateur utilisateur);
}
