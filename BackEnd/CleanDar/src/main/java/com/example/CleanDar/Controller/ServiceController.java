package com.example.CleanDar.Controller;


import com.example.CleanDar.Dto.ServiceDto;
import com.example.CleanDar.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")

public class ServiceController {

    private final ServiceService serviceCrudService;

    @Autowired
    public ServiceController(ServiceService serviceCrudService) {
        this.serviceCrudService = serviceCrudService;
    }

    @PostMapping("Admin/add")
    public ResponseEntity<ServiceDto> addService(@RequestBody ServiceDto serviceDto) {
        ServiceDto newService = serviceCrudService.addService(serviceDto);
        return new ResponseEntity<>(newService, HttpStatus.CREATED);
    }
}