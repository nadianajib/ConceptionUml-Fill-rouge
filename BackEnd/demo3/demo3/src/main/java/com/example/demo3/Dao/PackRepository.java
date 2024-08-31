package com.example.demo3.Dao;

import com.example.demo3.model.Pack;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PackRepository extends JpaRepository<Pack, Long> {
    // Méthodes personnalisées ici
}
