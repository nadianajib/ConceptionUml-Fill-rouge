package com.example.CleanDar.Service;

import com.example.CleanDar.Dao.ServiceNettoyageRepository;
import com.example.CleanDar.Dto.ServiceDto;
import com.example.CleanDar.model.ServiceNettoyage;
import com.example.CleanDar.model.TypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceNettoyageRepository serviceNettoyageRepository;

    @Override
    public ServiceDto addService(ServiceDto serviceDto) {
        ServiceNettoyage serviceNettoyage = convertToEntity(serviceDto);
        ServiceNettoyage savedServiceNettoyage = serviceNettoyageRepository.save(serviceNettoyage);
        return convertToDto(savedServiceNettoyage);
    }
    @Override
    public List<ServiceDto> getAllServices() {
        return serviceNettoyageRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public ServiceDto getServiceById(Long id) {
        ServiceNettoyage serviceNettoyage = serviceNettoyageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return convertToDto(serviceNettoyage);
    }
    @Override
    public ServiceDto updateService(Long id, ServiceDto serviceDto) {
        ServiceNettoyage existingServiceNettoyage = serviceNettoyageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        existingServiceNettoyage.setNom(serviceDto.getNom());
        existingServiceNettoyage.setDescription(serviceDto.getDescription());
        existingServiceNettoyage.setPrix(serviceDto.getPrix());
        existingServiceNettoyage.setImage(serviceDto.getImage());
        existingServiceNettoyage.setTypeService(TypeService.valueOf(serviceDto.getTypeService()));

        ServiceNettoyage updatedServiceNettoyage = serviceNettoyageRepository.save(existingServiceNettoyage);
        return convertToDto(updatedServiceNettoyage);
    }
    @Override
    public void deleteService(Long id) {
        serviceNettoyageRepository.deleteById(id);
    }

    private ServiceDto convertToDto(ServiceNettoyage serviceNettoyage) {
        ServiceDto dto = new ServiceDto();
        dto.setId(serviceNettoyage.getId());
        dto.setNom(serviceNettoyage.getNom());
        dto.setDescription(serviceNettoyage.getDescription());
        dto.setPrix(serviceNettoyage.getPrix());
        dto.setImage(serviceNettoyage.getImage());
        dto.setTypeService(serviceNettoyage.getTypeService().name());
        return dto;
    }

    private ServiceNettoyage convertToEntity(ServiceDto dto) {
        ServiceNettoyage serviceNettoyage = new ServiceNettoyage();
        serviceNettoyage.setNom(dto.getNom());
        serviceNettoyage.setDescription(dto.getDescription());
        serviceNettoyage.setPrix(dto.getPrix());
        serviceNettoyage.setImage(dto.getImage());
        serviceNettoyage.setTypeService(TypeService.valueOf(dto.getTypeService()));
        return serviceNettoyage;
    }

}