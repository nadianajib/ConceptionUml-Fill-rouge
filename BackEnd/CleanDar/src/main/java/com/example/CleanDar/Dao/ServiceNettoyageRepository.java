package com.example.CleanDar.Dao;

import com.example.CleanDar.Dto.PackDto;
import com.example.CleanDar.model.ServiceNettoyage;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ServiceNettoyageRepository extends JpaRepository<ServiceNettoyage, Long> {

    List<ServiceNettoyage> findAllByPack(Long packId);
}
