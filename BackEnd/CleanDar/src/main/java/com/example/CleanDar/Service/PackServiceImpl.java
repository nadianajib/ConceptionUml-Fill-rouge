package com.example.CleanDar.Service;



import com.example.CleanDar.Dao.PackRepository;
import com.example.CleanDar.Dao.ServiceNettoyageRepository;
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
    private ServiceNettoyageRepository serviceNettoyageRepository;

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
    public Pack createPack(PackDto packDto) {
        // Créer un nouvel objet Pack à partir du DTO
        Pack pack = new Pack();

        // Remplir les attributs du pack
        pack.setPrixTotal(0.0);
        pack.setReduction(packDto.getReduction());
        pack.setTitre(packDto.getTitre());
        pack.setDescription(packDto.getDescription());
        pack.setImage(packDto.getImage());

        // Enregistrer le pack dans la base de données
        return packRepository.save(pack);
    }
    @Override
    public Pack editPack(Long packId, PackDto packDto) {
        // Récupérer le pack existant par ID
        Pack pack = packRepository.findById(packId)
                .orElseThrow(() -> new RuntimeException("Pack non trouvé avec l'ID : " + packId));

        // Mettre à jour les attributs du pack avec ceux du DTO
        pack.setTitre(packDto.getTitre());
        pack.setDescription(packDto.getDescription());
        pack.setImage(packDto.getImage());

        modifierReduction(pack, packDto.getReduction());



        // Enregistrer les modifications dans la base de données
        return packRepository.save(pack);
    }

    public void modifierReduction(Pack pack, Double reduction) {
        Double prixTotal = pack.getPrixTotal();
        if (reduction <= 100) {
            Double reductionFinal = reduction / 100;
            Double montantReduction = prixTotal * reductionFinal;
            Double prixFinal = prixTotal - montantReduction;
            System.out.println("pt:" + prixTotal + " montantReduction: " + montantReduction + " prixFinal: " + prixFinal);

            pack.setPrixTotal(prixFinal);
            pack.setReduction(reduction);
        } else {
            System.out.println("reduction doit être inferieur ou égal à 100");
        }


    }

    @Override
    public void annulerPack(Long id) {
        packRepository.deleteById(id);
    }


    public Pack IncrementerPrixService(Long packId, Double prixService){
        PackDto dto = new PackDto();
        Pack pack = packRepository.findById(packId).get();
        Double prixTotal = pack.getPrixTotal();
        Double prixFinal = prixTotal + prixService;

        pack.setPrixTotal(prixFinal);
        return packRepository.save(pack);
    }
    public Pack DecrementerPrixService(Long packId, Double prixService){
        PackDto dto = new PackDto();
        Pack pack = packRepository.findById(packId).get();
        Double prixTotal = pack.getPrixTotal();
        Double prixFinal = prixTotal - prixService;
        pack.setPrixTotal(prixFinal);
        return packRepository.save(pack);
    }

//    public PackDto entityToDto(Pack pack) {
//        if (pack == null) {
//            return null; // or throw an exception based on your preference
//        }
//
//        PackDto dto = new PackDto();
//        dto.setId(pack.getId());
//        dto.setPrixTotal(pack.getPrixTotal());
//        dto.setReduction(pack.getReduction());
//        dto.setTitre(pack.getTitre());
//        dto.setDescription(pack.getDescription());
//        dto.setImage(pack.getImage());
//
//        // Mapping associated service IDs without using streams
//        if (pack.getServiceNettoyages() != null) {
//            List<Long> serviceIds = new ArrayList<>();
//            for (ServiceNettoyage serviceNettoyage : pack.getServiceNettoyages()) {
//                serviceIds.add(serviceNettoyage.getId());
//            }
//            dto.setServices(serviceIds);
//        }
//
//        return dto;
//    }


}
