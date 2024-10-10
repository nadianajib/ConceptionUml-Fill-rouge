package com.example.CleanDar.Service;

import com.example.CleanDar.Dao.PackRepository;
import com.example.CleanDar.Dao.ServiceNettoyageRepository;
import com.example.CleanDar.Dto.ServiceNettoyageDto;
import com.example.CleanDar.model.Pack;
import com.example.CleanDar.model.ServiceNettoyage;
import com.example.CleanDar.model.TypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private PackRepository packRepository;
    @Autowired
    private ServiceNettoyageRepository serviceNettoyageRepository;
    @Autowired
    private PackServiceImpl packServiceImpl;


    @Override
    public List<ServiceNettoyageDto> getAllServicesByPackId(Long packId) {
        // Récupérer tous les services par l'ID du pack
        return serviceNettoyageRepository.findAllByPackId(packId).stream()
                .map(service -> {
                    ServiceNettoyageDto dto = new ServiceNettoyageDto();
                    dto.setId(service.getId());
                    dto.setNom(service.getNom());
                    dto.setDescription(service.getDescription());
                    dto.setPrix(service.getPrix());
                    dto.setImage(service.getImage());
                    dto.setTypeService(service.getTypeService());
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ServiceNettoyageDto addService(ServiceNettoyageDto serviceNettoyageDto) {
        ServiceNettoyage serviceNettoyage = convertToEntity(serviceNettoyageDto);

        // Vérifier si pack_id est null et ne pas l'associer si c'est le cas
        if (serviceNettoyageDto.getPack_id() != null) {
            Pack pack = packRepository.findById(serviceNettoyageDto.getPack_id())
                    .orElseThrow(() -> new RuntimeException("Pack non trouvé"));
            serviceNettoyage.setPack(pack);
        } else {
            serviceNettoyage.setPack(null); // S'assurer que le pack est null
        }

        ServiceNettoyage savedServiceNettoyage = serviceNettoyageRepository.save(serviceNettoyage);
        //Tester si l'ajout est effectuer et le pack existe
        if(savedServiceNettoyage.getId()!=null && savedServiceNettoyage.getPack()!=null){
            Long packId=savedServiceNettoyage.getPack().getId();
            packServiceImpl.IncrementerPrixService(packId,savedServiceNettoyage.getPrix());
        }
        return convertToDto(savedServiceNettoyage);
    }


    @Override
    public List<ServiceNettoyageDto> getAllServices() {
        return serviceNettoyageRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public ServiceNettoyageDto getServiceById(Long id) {
        ServiceNettoyage serviceNettoyage = serviceNettoyageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return convertToDto(serviceNettoyage);
    }
    @Override
    public ServiceNettoyageDto updateService(Long id, ServiceNettoyageDto serviceNettoyageDto) {
        ServiceNettoyage existingServiceNettoyage = serviceNettoyageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        existingServiceNettoyage.setNom(serviceNettoyageDto.getNom());
        existingServiceNettoyage.setDescription(serviceNettoyageDto.getDescription());
        existingServiceNettoyage.setPrix(serviceNettoyageDto.getPrix());
        existingServiceNettoyage.setImage(serviceNettoyageDto.getImage());
        existingServiceNettoyage.setTypeService(serviceNettoyageDto.getTypeService());

        ServiceNettoyage updatedServiceNettoyage = serviceNettoyageRepository.save(existingServiceNettoyage);

        Long packId=existingServiceNettoyage.getPack().getId();

        packServiceImpl.IncrementerPrixService(packId,existingServiceNettoyage.getPrix());
        return convertToDto(updatedServiceNettoyage);
    }
    @Override
    public void deleteService(Long id) {
        ServiceNettoyage serviceNettoyage = serviceNettoyageRepository.findById(id).get();
        if(serviceNettoyage.getId()!=null && serviceNettoyage.getPack()!=null){
            Long packId=serviceNettoyage.getPack().getId();
            packServiceImpl.DecrementerPrixService(packId,serviceNettoyage.getPrix());
        }
        serviceNettoyageRepository.deleteById(id);
    }

    private ServiceNettoyageDto convertToDto(ServiceNettoyage service) {
        ServiceNettoyageDto dto = new ServiceNettoyageDto();
        dto.setId(service.getId());
        dto.setNom(service.getNom());
        dto.setDescription(service.getDescription());
        dto.setPrix(service.getPrix());
        dto.setImage(service.getImage());
        dto.setTypeService(service.getTypeService());

        // Vérifier si le pack est null avant d'accéder à ses attributs
        if (service.getPack() != null) {
            dto.setPack_id(service.getPack().getId());
        } else {
            dto.setPack_id(null);  // Ou vous pouvez choisir de ne pas définir cet attribut
        }
        return dto;
    }


    private ServiceNettoyage convertToEntity(ServiceNettoyageDto dto) {
        ServiceNettoyage serviceNettoyage = new ServiceNettoyage();
        serviceNettoyage.setNom(dto.getNom());
        serviceNettoyage.setDescription(dto.getDescription());
        serviceNettoyage.setPrix(dto.getPrix());
        serviceNettoyage.setImage(dto.getImage());
        serviceNettoyage.setTypeService(dto.getTypeService());
        serviceNettoyage.setPack(packRepository.getById(dto.getPack_id()));
        return serviceNettoyage;
    }

}