package com.example.CleanDar.Service;

import com.example.CleanDar.Dao.ServiceRepository;
import com.example.CleanDar.Dto.ServiceDto;
import com.example.CleanDar.model.Service;
import com.example.CleanDar.model.TypeService;
import org.springframework.beans.factory.annotation.Autowired;




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