package com.example.CleanDar.Service;

import com.example.CleanDar.Dao.PackRepository;
import com.example.CleanDar.Dao.ServiceNettoyageRepository;
import com.example.CleanDar.Dto.ServiceNettoyageDto;
import com.example.CleanDar.model.Pack;
import com.example.CleanDar.model.ServiceNettoyage;
import com.example.CleanDar.model.TypeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ServiceServiceImplTest {

    @Mock
    private PackRepository packRepository;

    @Mock
    private ServiceNettoyageRepository serviceNettoyageRepository;

    @Mock
    private PackServiceImpl packServiceImpl;

    @InjectMocks
    private ServiceServiceImpl serviceService;

    private ServiceNettoyage serviceNettoyage;

    @BeforeEach
    void setUp() {
        serviceNettoyage = new ServiceNettoyage();
        serviceNettoyage.setId(1L);
        serviceNettoyage.setNom("Nettoyage Standard");
        serviceNettoyage.setDescription("Nettoyage complet");
        serviceNettoyage.setPrix(100.0);
        serviceNettoyage.setImage("image.png");
        serviceNettoyage.setTypeService(TypeService.NETTOYAGESTANDARD);
    }

    @Test
    void getAllServicesByPackId() {
        when(serviceNettoyageRepository.findAllByPackId(1L)).thenReturn(Arrays.asList(serviceNettoyage));

        var result = serviceService.getAllServicesByPackId(1L);

        assertEquals(1, result.size());
        assertEquals("Nettoyage Standard", result.get(0).getNom());
        verify(serviceNettoyageRepository).findAllByPackId(1L);
    }

    @Test
    void addService() {
        ServiceNettoyageDto dto = new ServiceNettoyageDto();
        dto.setNom("Nettoyage Standard");
        dto.setDescription("Nettoyage complet");
        dto.setPrix(100.0);
        dto.setImage("image.png");
        dto.setPack_id(null); // No associated pack

        when(serviceNettoyageRepository.save(any(ServiceNettoyage.class))).thenReturn(serviceNettoyage);

        ServiceNettoyageDto result = serviceService.addService(dto);

        assertNotNull(result);
        assertEquals("Nettoyage Standard", result.getNom());
        verify(serviceNettoyageRepository).save(any(ServiceNettoyage.class));
    }

    @Test
    void getAllServices() {
        when(serviceNettoyageRepository.findAll()).thenReturn(Arrays.asList(serviceNettoyage));

        var result = serviceService.getAllServices();

        assertEquals(1, result.size());
        assertEquals("Nettoyage Standard", result.get(0).getNom());
        verify(serviceNettoyageRepository).findAll();
    }

    @Test
    void getServiceById() {
        when(serviceNettoyageRepository.findById(1L)).thenReturn(Optional.of(serviceNettoyage));

        ServiceNettoyageDto result = serviceService.getServiceById(1L);

        assertNotNull(result);
        assertEquals("Nettoyage Standard", result.getNom());
        verify(serviceNettoyageRepository).findById(1L);
    }

    @Test
    void updateService() {
        // Arrange
        Pack pack = new Pack(); // Create a new Pack instance
        pack.setId(1L); // Set a valid ID for the pack

        serviceNettoyage.setPack(pack); // Associate the pack with the serviceNettoyage

        ServiceNettoyageDto dto = new ServiceNettoyageDto();
        dto.setNom("Updated Service");
        dto.setDescription("Updated description");
        dto.setPrix(120.0);
        dto.setImage("updated_image.png");
        dto.setPack_id(pack.getId()); // Set the associated pack ID

        when(serviceNettoyageRepository.findById(1L)).thenReturn(Optional.of(serviceNettoyage));
        when(serviceNettoyageRepository.save(any(ServiceNettoyage.class))).thenReturn(serviceNettoyage);

        // Act
        ServiceNettoyageDto result = serviceService.updateService(1L, dto);

        // Assert
        assertNotNull(result);
        assertEquals("Updated Service", result.getNom());
        verify(serviceNettoyageRepository).findById(1L);
        verify(serviceNettoyageRepository).save(any(ServiceNettoyage.class));
    }


    @Test
    void deleteService() {
        Pack pack = new Pack();
        pack.setId(1L); // Set a valid ID
        pack.setPrixTotal(100.0);

        serviceNettoyage.setPack(pack); // Associate the service with a pack

        when(serviceNettoyageRepository.findById(1L)).thenReturn(Optional.of(serviceNettoyage));

        serviceService.deleteService(1L);

        verify(serviceNettoyageRepository).deleteById(1L);
        verify(packServiceImpl).DecrementerPrixService(pack.getId(), serviceNettoyage.getPrix());
    }

}
