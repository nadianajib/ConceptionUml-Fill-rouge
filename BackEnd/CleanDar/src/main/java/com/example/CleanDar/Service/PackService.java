package com.example.CleanDar.Service;

import com.example.CleanDar.Dto.PackDto;
import com.example.CleanDar.model.Pack;

import java.util.List;

public interface PackService {
//    PackDto createPack(PackDto packDTO);
    List<PackDto> getAllPacks(); // Ajoutez cette ligne


    void annulerPack(Long id);

//    Pack creerPack(List<Long> serviceIds, Double reduction);

    Pack creerPack(PackDto packDto);

    Pack modifierReduction(PackDto packDto, Double reduction);
}
