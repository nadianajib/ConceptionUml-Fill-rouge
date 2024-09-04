package com.example.CleanDar.Dao;


import com.example.CleanDar.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // Méthodes personnalisées ici
}
