package com.example.CleanDar.Service;

import com.example.CleanDar.Dao.ServiceNettoyageRepository;
import com.example.CleanDar.Dto.ServiceNettoyageDto;
import com.example.CleanDar.model.ServiceNettoyage;
import com.example.CleanDar.model.TypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceNettoyageRepository serviceNettoyageRepository;


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
                    dto.setTypeService(String.valueOf(service.getTypeService()));
                    return dto;
                })
                .collect(Collectors.toList());
    }
    @Override
    public ServiceNettoyageDto addService(ServiceNettoyageDto serviceNettoyageDto) {
        ServiceNettoyage serviceNettoyage = convertToEntity(serviceNettoyageDto);
        ServiceNettoyage savedServiceNettoyage = serviceNettoyageRepository.save(serviceNettoyage);
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
        existingServiceNettoyage.setTypeService(TypeService.valueOf(serviceNettoyageDto.getTypeService()));

        ServiceNettoyage updatedServiceNettoyage = serviceNettoyageRepository.save(existingServiceNettoyage);
        return convertToDto(updatedServiceNettoyage);
    }
    @Override
    public void deleteService(Long id) {
        serviceNettoyageRepository.deleteById(id);
    }

    private ServiceNettoyageDto convertToDto(ServiceNettoyage serviceNettoyage) {
        ServiceNettoyageDto dto = new ServiceNettoyageDto();
        dto.setId(serviceNettoyage.getId());
        dto.setNom(serviceNettoyage.getNom());
        dto.setDescription(serviceNettoyage.getDescription());
        dto.setPrix(serviceNettoyage.getPrix());
        dto.setImage(serviceNettoyage.getImage());
        dto.setTypeService(serviceNettoyage.getTypeService().name());
        return dto;
    }

    private ServiceNettoyage convertToEntity(ServiceNettoyageDto dto) {
        ServiceNettoyage serviceNettoyage = new ServiceNettoyage();
        serviceNettoyage.setNom(dto.getNom());
        serviceNettoyage.setDescription(dto.getDescription());
        serviceNettoyage.setPrix(dto.getPrix());
        serviceNettoyage.setImage(dto.getImage());
        serviceNettoyage.setTypeService(TypeService.valueOf(dto.getTypeService()));
        return serviceNettoyage;
    }

}