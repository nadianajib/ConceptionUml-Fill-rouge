package com.example.CleanDar.Service;



import com.example.CleanDar.Dao.PackRepository;
import com.example.CleanDar.Dto.PackDto;
import com.example.CleanDar.model.Pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackServiceImpl implements PackService {

    @Autowired
    private PackRepository packRepository;

    @Override
    public PackDto createPack(PackDto packDTO) {
        // Convert DTO to entity
        Pack pack = new Pack();
        pack.setPrixTotal(packDTO.getPrixTotal());
        pack.setReduction(packDTO.getReduction());
        pack.setTitre(packDTO.getTitre());
        pack.setImage(packDTO.getImage());
        pack.setDescription(packDTO.getDescription());

        // Save entity
        Pack savedPack = packRepository.save(pack);

        // Convert entity back to DTO
        PackDto result = new PackDto();
        result.setId(savedPack.getId());
        result.setPrixTotal(savedPack.getPrixTotal());
        result.setReduction(savedPack.getReduction());
        result.setTitre(savedPack.getTitre());
        result.setImage(savedPack.getImage());
        result.setDescription(savedPack.getDescription());

        return result;
    }

    @Override
    public List<PackDto> getAllPacks() {
        List<Pack> packs = packRepository.findAll();
        List<PackDto> packDtos = new ArrayList<>();

        for (Pack pack : packs) {
            PackDto dto = new PackDto();
            dto.setId(pack.getId());
            dto.setPrixTotal(pack.getPrixTotal());
            dto.setReduction(pack.getReduction());
            dto.setTitre(pack.getTitre());
            dto.setDescription(pack.getDescription());
            dto.setImage(pack.getImage());
            packDtos.add(dto);
        }

        return packDtos;
    }


}
