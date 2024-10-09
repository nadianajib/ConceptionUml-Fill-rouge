package com.example.CleanDar.Service;

import com.example.CleanDar.Dto.PackDto;
import com.example.CleanDar.model.Pack;

import java.util.List;

public interface PackService {
    List<PackDto> getAllPacks(); // Ajoutez cette ligne

    void annulerPack(Long id);

    Pack createPack(PackDto packDto);

    Pack editPack(Long packId, PackDto packDto);
}
