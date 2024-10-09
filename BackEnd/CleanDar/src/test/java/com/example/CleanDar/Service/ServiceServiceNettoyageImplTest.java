//package com.example.CleanDar.Service;
//
//import com.example.CleanDar.Dto.ServiceNettoyageDto;
//import com.example.CleanDar.model.TypeService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//@SpringBootTest
//@Transactional
//public class ServiceServiceNettoyageImplTest {
//
//    @Autowired
//    private ServiceServiceImpl serviceService;
//
//    @BeforeEach
//    public void setUp() {
//        // On vide la base de données avant chaque test
//        List<ServiceNettoyageDto> services = serviceService.getAllServices();
//        for (ServiceNettoyageDto service : services) {
//            serviceService.deleteService(service.getId());
//        }
//    }
//
//    @Test
//    public void testAddService() {
//        ServiceNettoyageDto serviceNettoyageDto = new ServiceNettoyageDto();
//        serviceNettoyageDto.setNom("Service Nettoyage");
//        serviceNettoyageDto.setDescription("Nettoyage standard");
//        serviceNettoyageDto.setPrix(50.0);
//        serviceNettoyageDto.setImage("image1.jpg");
//        serviceNettoyageDto.setTypeService(TypeService.NETTOYAGESTANDARD.name());
//
//        ServiceNettoyageDto savedService = serviceService.addService(serviceNettoyageDto);
//
//        assertNotNull(savedService.getId());
//        assertEquals("Service Nettoyage", savedService.getNom());
//        assertEquals("Nettoyage standard", savedService.getDescription());
//        assertEquals(50.0, savedService.getPrix());
//        assertEquals("image1.jpg", savedService.getImage());
//        assertEquals(TypeService.NETTOYAGESTANDARD.name(), savedService.getTypeService());
//    }
//
//    @Test
//    public void testGetAllServices() {
//        ServiceNettoyageDto service1 = new ServiceNettoyageDto();
//        service1.setNom("Service Cuisine");
//        service1.setDescription("Nettoyage de cuisine");
//        service1.setPrix(70.0);
//        service1.setImage("image2.jpg");
//        service1.setTypeService(TypeService.CUISINE.name());
//
//        ServiceNettoyageDto service2 = new ServiceNettoyageDto();
//        service2.setNom("Service Salle de Bain");
//        service2.setDescription("Nettoyage salle de bain");
//        service2.setPrix(60.0);
//        service2.setImage("image3.jpg");
//        service2.setTypeService(TypeService.SALLE_DE_BAIN.name());
//
//        serviceService.addService(service1);
//        serviceService.addService(service2);
//
//        List<ServiceNettoyageDto> services = serviceService.getAllServices();
//
//        assertEquals(2, services.size());
//    }
//
//    @Test
//    public void testGetServiceById() {
//        ServiceNettoyageDto serviceNettoyageDto = new ServiceNettoyageDto();
//        serviceNettoyageDto.setNom("Service Cuisine");
//        serviceNettoyageDto.setDescription("Nettoyage cuisine");
//        serviceNettoyageDto.setPrix(100.0);
//        serviceNettoyageDto.setImage("image.jpg");
//        serviceNettoyageDto.setTypeService(TypeService.CUISINE.name());
//
//        ServiceNettoyageDto savedService = serviceService.addService(serviceNettoyageDto);
//
//        ServiceNettoyageDto foundService = serviceService.getServiceById(savedService.getId());
//
//        assertNotNull(foundService);
//        assertEquals(savedService.getNom(), foundService.getNom());
//        assertEquals(savedService.getTypeService(), foundService.getTypeService());
//    }
//
//    @Test
//    public void testUpdateService() {
//        ServiceNettoyageDto serviceNettoyageDto = new ServiceNettoyageDto();
//        serviceNettoyageDto.setNom("Service Cuisine");
//        serviceNettoyageDto.setDescription("Nettoyage cuisine");
//        serviceNettoyageDto.setPrix(100.0);
//        serviceNettoyageDto.setImage("image.jpg");
//        serviceNettoyageDto.setTypeService(TypeService.CUISINE.name());
//
//        ServiceNettoyageDto savedService = serviceService.addService(serviceNettoyageDto);
//
//        ServiceNettoyageDto updateDto = new ServiceNettoyageDto();
//        updateDto.setNom("Service Cuisine Modifié");
//        updateDto.setDescription("Nettoyage cuisine modifié");
//        updateDto.setPrix(150.0);
//        updateDto.setImage("image_modifiee.jpg");
//        updateDto.setTypeService(TypeService.CUISINE.name());
//
//        ServiceNettoyageDto updatedService = serviceService.updateService(savedService.getId(), updateDto);
//
//        assertNotNull(updatedService);
//        assertEquals("Service Cuisine Modifié", updatedService.getNom());
//        assertEquals("Nettoyage cuisine modifié", updatedService.getDescription());
//        assertEquals(150.0, updatedService.getPrix());
//        assertEquals("image_modifiee.jpg", updatedService.getImage());
//    }
//
//    @Test
//    public void testDeleteService() {
//        ServiceNettoyageDto serviceNettoyageDto = new ServiceNettoyageDto();
//        serviceNettoyageDto.setNom("Service Cuisine");
//        serviceNettoyageDto.setDescription("Nettoyage cuisine");
//        serviceNettoyageDto.setPrix(100.0);
//        serviceNettoyageDto.setImage("image.jpg");
//        serviceNettoyageDto.setTypeService(TypeService.CUISINE.name());
//
//        ServiceNettoyageDto savedService = serviceService.addService(serviceNettoyageDto);
//
//        serviceService.deleteService(savedService.getId());
//
//        RuntimeException exception = assertThrows(RuntimeException.class, () -> {
//            serviceService.getServiceById(savedService.getId());
//        });
//
//        assertEquals("Service not found", exception.getMessage());
//    }
//}
