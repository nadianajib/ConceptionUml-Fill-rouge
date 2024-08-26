package com.example.demo3.Dao;

import com.example.demo3.model.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur, Long> {
    // Vous pouvez définir des méthodes personnalisées ici si nécessaire
}