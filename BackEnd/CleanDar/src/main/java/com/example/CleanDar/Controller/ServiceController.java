package com.example.CleanDar.Controller;


import com.example.CleanDar.Dao.ServiceNettoyageRepository;
import com.example.CleanDar.Dto.ServiceNettoyageDto;
import com.example.CleanDar.Service.ServiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/services")
@CrossOrigin(origins = "http://localhost:4200")

public class ServiceController {

    private final ServiceService serviceCrudService;
    @Autowired
    public ServiceController(ServiceService serviceCrudService) {
        this.serviceCrudService = serviceCrudService;
    }
    @Autowired
    private ServiceService serviceService;

    @PostMapping("/add")
    public ResponseEntity<ServiceNettoyageDto> addService(@RequestBody ServiceNettoyageDto serviceNettoyageDto) {
        try {
            ServiceNettoyageDto newService = serviceCrudService.addService(serviceNettoyageDto);
            return new ResponseEntity<>(newService, HttpStatus.CREATED);
        } catch (Exception e) {
            // Gérer les exceptions (par exemple, pack non trouvé, etc.)
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/all")
    public ResponseEntity<List<ServiceNettoyageDto>> getAllServices() {
        List<ServiceNettoyageDto> services = serviceCrudService.getAllServices();
        return ResponseEntity.ok(services);
    }

    @GetMapping("/pack/{packId}")
    public ResponseEntity<List<ServiceNettoyageDto>> getServicesByPackId(@PathVariable Long packId) {
        List<ServiceNettoyageDto> services = serviceService.getAllServicesByPackId(packId);
        return ResponseEntity.ok(services);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ServiceNettoyageDto> getServiceById(@PathVariable Long id) {
        try {
            ServiceNettoyageDto service = serviceCrudService.getServiceById(id);
            return ResponseEntity.ok(service);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<ServiceNettoyageDto> updateService(@PathVariable Long id, @RequestBody ServiceNettoyageDto serviceNettoyageDto) {
        try {
            ServiceNettoyageDto updatedService = serviceCrudService.updateService(id, serviceNettoyageDto);
            return ResponseEntity.ok(updatedService);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("Delete/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        try {
            serviceCrudService.deleteService(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}