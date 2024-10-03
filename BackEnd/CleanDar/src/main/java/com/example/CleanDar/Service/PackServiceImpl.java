package com.example.CleanDar.Service;



import com.example.CleanDar.Dao.PackRepository;
import com.example.CleanDar.Dao.ServiceRepository;
import com.example.CleanDar.Dto.PackDto;
import com.example.CleanDar.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PackServiceImpl implements PackService {

    @Autowired
    private PackRepository packRepository;

    @Autowired
    private PackService packService;
    @Autowired
    private ServiceRepository serviceRepository;

//
//    @Override
//    public PackDto createPack(PackDto packDTO) {
//        // Convert DTO to entity
//        Pack pack = new Pack();
//        pack.setPrixTotal(packDTO.getPrixTotal());
//        pack.setReduction(packDTO.getReduction());
//        pack.setTitre(packDTO.getTitre());
//        pack.setImage(packDTO.getImage());
//        pack.setDescription(packDTO.getDescription());
//
//        // Save entity
//        Pack savedPack = packRepository.save(pack);
//
//        // Convert entity back to DTO
//        PackDto result = new PackDto();
//        result.setId(savedPack.getId());
//        result.setPrixTotal(savedPack.getPrixTotal());
//        result.setReduction(savedPack.getReduction());
//        result.setTitre(savedPack.getTitre());
//        result.setImage(savedPack.getImage());
//        result.setDescription(savedPack.getDescription());
//
//        return result;
//    }

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


    @Override
    public void annulerPack(Long id) {
        packRepository.deleteById(id);
    }


    @Override
    public Pack creerPack(List<Long> serviceIds) {
        // Récupérer tous les services par leur ID et calculer le prix total
        Double prixTotal = 0.0;

        for (Long serviceId : serviceIds) {
            // Trouver le service par ID
//            Service service = serviceRepository.findById(serviceId).orElse(null);
            com.example.CleanDar.model.Service service = serviceRepository.findById(serviceId).orElse(null);
            if (service != null) {
                // Ajouter le prix du service au prix total
                prixTotal += service.getPrix();

            }
        }

        // Calculer la réduction
        Double reduction = 0.20; // 20%
        Double montantReduction = prixTotal * reduction; // Montant de la réduction
        Double prixFinal = prixTotal - montantReduction; // Prix final après réduction

        // Remplir les détails du pack
        Pack pack = new Pack();
        pack.setTitre("Pack de Services");
        pack.setDescription("Un pack comprenant plusieurs services.");
        pack.setPrixTotal(prixFinal); // Mettre à jour le prix total après réduction
        pack.setReduction(montantReduction); // Stocker le montant de la réduction

        // Sauvegarder le pack dans la base de données
        return packRepository.save(pack);
    }
}
