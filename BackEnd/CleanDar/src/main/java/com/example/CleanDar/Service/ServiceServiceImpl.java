package com.example.CleanDar.Service;

import com.example.CleanDar.Dao.ServiceRepository;
import com.example.CleanDar.Dto.ServiceDto;
import com.example.CleanDar.model.Service;
import com.example.CleanDar.model.TypeService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;


@org.springframework.stereotype.Service
public class ServiceServiceImpl implements ServiceService {

    @Autowired
    private ServiceRepository serviceRepository;

    @Override
    public ServiceDto addService(ServiceDto serviceDto) {
        Service service = convertToEntity(serviceDto);
        Service savedService = serviceRepository.save(service);
        return convertToDto(savedService);
    }
    @Override
    public List<ServiceDto> getAllServices() {
        return serviceRepository.findAll().stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
    }
    @Override
    public ServiceDto getServiceById(Long id) {
        Service service = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));
        return convertToDto(service);
    }
    @Override
    public ServiceDto updateService(Long id, ServiceDto serviceDto) {
        Service existingService = serviceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Service not found"));

        existingService.setNom(serviceDto.getNom());
        existingService.setDescription(serviceDto.getDescription());
        existingService.setPrix(serviceDto.getPrix());
        existingService.setImage(serviceDto.getImage());
        existingService.setTypeService(TypeService.valueOf(serviceDto.getTypeService()));

        Service updatedService = serviceRepository.save(existingService);
        return convertToDto(updatedService);
    }
    private ServiceDto convertToDto(Service service) {
        ServiceDto dto = new ServiceDto();
        dto.setId(service.getId());
        dto.setNom(service.getNom());
        dto.setDescription(service.getDescription());
        dto.setPrix(service.getPrix());
        dto.setImage(service.getImage());
        dto.setTypeService(service.getTypeService().name());
        return dto;
    }

    private Service convertToEntity(ServiceDto dto) {
        Service service = new Service();
        service.setNom(dto.getNom());
        service.setDescription(dto.getDescription());
        service.setPrix(dto.getPrix());
        service.setImage(dto.getImage());
        service.setTypeService(TypeService.valueOf(dto.getTypeService()));
        return service;
    }
}