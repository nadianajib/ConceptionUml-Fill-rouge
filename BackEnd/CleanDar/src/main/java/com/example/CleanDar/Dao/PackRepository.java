package com.example.CleanDar.Dao;


import com.example.CleanDar.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackRepository extends JpaRepository<Pack, Long> {
    // Méthodes personnalisées ici
}
