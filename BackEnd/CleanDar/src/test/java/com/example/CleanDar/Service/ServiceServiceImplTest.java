package com.example.CleanDar.Service;

import com.example.CleanDar.Dto.ServiceDto;
import com.example.CleanDar.model.TypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
public class ServiceServiceImplTest {

    @Autowired
    private ServiceServiceImpl serviceService;

    @BeforeEach
    public void setUp() {
        // On vide la base de données avant chaque test
        List<ServiceDto> services = serviceService.getAllServices();
        for (ServiceDto service : services) {
            serviceService.deleteService(service.getId());
        }
    }

    @Test
    public void testAddService() {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setNom("Service Nettoyage");
        serviceDto.setDescription("Nettoyage standard");
        serviceDto.setPrix(50.0);
        serviceDto.setImage("image1.jpg");
        serviceDto.setTypeService(TypeService.NETTOYAGESTANDARD.name());

        ServiceDto savedService = serviceService.addService(serviceDto);

        assertNotNull(savedService.getId());
        assertEquals("Service Nettoyage", savedService.getNom());
        assertEquals("Nettoyage standard", savedService.getDescription());
        assertEquals(50.0, savedService.getPrix());
        assertEquals("image1.jpg", savedService.getImage());
        assertEquals(TypeService.NETTOYAGESTANDARD.name(), savedService.getTypeService());
    }

    @Test
    public void testGetAllServices() {
        ServiceDto service1 = new ServiceDto();
        service1.setNom("Service Cuisine");
        service1.setDescription("Nettoyage de cuisine");
        service1.setPrix(70.0);
        service1.setImage("image2.jpg");
        service1.setTypeService(TypeService.CUISINE.name());

        ServiceDto service2 = new ServiceDto();
        service2.setNom("Service Salle de Bain");
        service2.setDescription("Nettoyage salle de bain");
        service2.setPrix(60.0);
        service2.setImage("image3.jpg");
        service2.setTypeService(TypeService.SALLE_DE_BAIN.name());

        serviceService.addService(service1);
        serviceService.addService(service2);

        List<ServiceDto> services = serviceService.getAllServices();

        assertEquals(2, services.size());
    }

    @Test
    public void testGetServiceById() {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setNom("Service Cuisine");
        serviceDto.setDescription("Nettoyage cuisine");
        serviceDto.setPrix(100.0);
        serviceDto.setImage("image.jpg");
        serviceDto.setTypeService(TypeService.CUISINE.name());

        ServiceDto savedService = serviceService.addService(serviceDto);

        ServiceDto foundService = serviceService.getServiceById(savedService.getId());

        assertNotNull(foundService);
        assertEquals(savedService.getNom(), foundService.getNom());
        assertEquals(savedService.getTypeService(), foundService.getTypeService());
    }

    @Test
    public void testUpdateService() {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setNom("Service Cuisine");
        serviceDto.setDescription("Nettoyage cuisine");
        serviceDto.setPrix(100.0);
        serviceDto.setImage("image.jpg");
        serviceDto.setTypeService(TypeService.CUISINE.name());

        ServiceDto savedService = serviceService.addService(serviceDto);

        ServiceDto updateDto = new ServiceDto();
        updateDto.setNom("Service Cuisine Modifié");
        updateDto.setDescription("Nettoyage cuisine modifié");
        updateDto.setPrix(150.0);
        updateDto.setImage("image_modifiee.jpg");
        updateDto.setTypeService(TypeService.CUISINE.name());

        ServiceDto updatedService = serviceService.updateService(savedService.getId(), updateDto);

        assertNotNull(updatedService);
        assertEquals("Service Cuisine Modifié", updatedService.getNom());
        assertEquals("Nettoyage cuisine modifié", updatedService.getDescription());
        assertEquals(150.0, updatedService.getPrix());
        assertEquals("image_modifiee.jpg", updatedService.getImage());
    }

    @Test
    public void testDeleteService() {
        ServiceDto serviceDto = new ServiceDto();
        serviceDto.setNom("Service Cuisine");
        serviceDto.setDescription("Nettoyage cuisine");
        serviceDto.setPrix(100.0);
        serviceDto.setImage("image.jpg");
        serviceDto.setTypeService(TypeService.CUISINE.name());

        ServiceDto savedService = serviceService.addService(serviceDto);

        serviceService.deleteService(savedService.getId());

        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
            serviceService.getServiceById(savedService.getId());
        });

        assertEquals("Service not found", exception.getMessage());
    }
}
