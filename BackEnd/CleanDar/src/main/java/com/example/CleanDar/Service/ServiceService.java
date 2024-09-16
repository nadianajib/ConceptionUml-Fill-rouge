package com.example.CleanDar.Service;

import com.example.CleanDar.Dto.ServiceDto;
import com.example.CleanDar.model.Service;

public interface ServiceService {

    // Ajouter un service
    ServiceDto addService(ServiceDto serviceDto);
}
