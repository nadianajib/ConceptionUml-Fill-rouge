package com.example.CleanDar.Service;

import com.example.CleanDar.Dto.PackDto;

import java.util.List;

public interface PackService {
    PackDto createPack(PackDto packDTO);
    List<PackDto> getAllPacks(); // Ajoutez cette ligne

}
