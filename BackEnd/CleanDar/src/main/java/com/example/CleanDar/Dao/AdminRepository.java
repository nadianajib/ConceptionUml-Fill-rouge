package com.example.CleanDar.Dao;


import com.example.CleanDar.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Admin, Long> {
}