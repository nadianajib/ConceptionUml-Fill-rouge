package com.example.CleanDar.Service;

import com.example.CleanDar.Dto.ServiceNettoyageDto;

import java.util.List;

public interface ServiceService {

    // Ajouter un service
    ServiceNettoyageDto addService(ServiceNettoyageDto serviceNettoyageDto);
    List<ServiceNettoyageDto> getAllServices();
    ServiceNettoyageDto getServiceById(Long id);
    ServiceNettoyageDto updateService(Long id, ServiceNettoyageDto serviceNettoyageDto);
    void deleteService(Long id);
    List<ServiceNettoyageDto> getAllServicesByPackId(Long packId);


}
