package com.example.CleanDar.Service;



import com.example.CleanDar.Dao.PackRepository;
import com.example.CleanDar.Dto.PackDto;
import com.example.CleanDar.model.Pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
        pack.setDescription(packDTO.getDescription());

        // Save entity
        Pack savedPack = packRepository.save(pack);

        // Convert entity back to DTO
        PackDto result = new PackDto();
        result.setId(savedPack.getId());
        result.setPrixTotal(savedPack.getPrixTotal());
        result.setReduction(savedPack.getReduction());
        result.setTitre(savedPack.getTitre());
        result.setDescription(savedPack.getDescription());

        return result;
    }
}
