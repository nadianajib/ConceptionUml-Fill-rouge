package com.example.CleanDar.Controller;


import com.example.CleanDar.Dto.ServiceDto;
import com.example.CleanDar.Service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services/Admin")

public class ServiceController {

    private final ServiceService serviceCrudService;

    @Autowired
    public ServiceController(ServiceService serviceCrudService) {
        this.serviceCrudService = serviceCrudService;
    }

    @PostMapping("/add")
    public ResponseEntity<ServiceDto> addService(@RequestBody ServiceDto serviceDto) {
        ServiceDto newService = serviceCrudService.addService(serviceDto);
        return new ResponseEntity<>(newService, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceDto>> getAllServices() {
        List<ServiceDto> services = serviceCrudService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> getServiceById(@PathVariable Long id) {
        try {
            ServiceDto service = serviceCrudService.getServiceById(id);
            return ResponseEntity.ok(service);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ServiceDto> updateService(@PathVariable Long id, @RequestBody ServiceDto serviceDto) {
        try {
            ServiceDto updatedService = serviceCrudService.updateService(id, serviceDto);
            return ResponseEntity.ok(updatedService);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}