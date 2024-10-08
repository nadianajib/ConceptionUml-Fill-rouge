package com.example.CleanDar.Service;

import com.example.CleanDar.Dto.ServiceDto;

import java.util.List;

public interface ServiceService {

    // Ajouter un service
    ServiceDto addService(ServiceDto serviceDto);
    List<ServiceDto> getAllServices();
    ServiceDto getServiceById(Long id);
    ServiceDto updateService(Long id, ServiceDto serviceDto);
    void deleteService(Long id);



}
