package com.example.CleanDar.Service;

import com.example.CleanDar.Dao.PackRepository;
import com.example.CleanDar.Dao.ServiceNettoyageRepository;
import com.example.CleanDar.Dto.PackDto;
import com.example.CleanDar.model.Pack;
import com.example.CleanDar.model.ServiceNettoyage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class PackServiceImplTest {

    @InjectMocks
    private PackServiceImpl packService;

    @Mock
    private PackRepository packRepository;

    @Mock
    private ServiceNettoyageRepository serviceNettoyageRepository;

    @Mock
    private ServiceServiceImpl serviceServiceImpl;

    private Pack pack;
    private PackDto packDto;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Initialize a sample Pack object
        pack = new Pack();
        pack.setId(1L);
        pack.setPrixTotal(200.0);
        pack.setReduction(10.0);
        pack.setTitre("Pack Test");
        pack.setDescription("Description of the pack");
        pack.setImage("pack_image.png");

        // Initialize a sample PackDto object
        packDto = new PackDto();
        packDto.setTitre("Updated Pack");
        packDto.setDescription("Updated description");
        packDto.setImage("updated_image.png");
        packDto.setReduction(15.0);
    }

    @Test
    void getAllPacks() {
        List<Pack> packs = new ArrayList<>();
        packs.add(pack);

        when(packRepository.findAll()).thenReturn(packs);

        List<PackDto> result = packService.getAllPacks();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(pack.getTitre(), result.get(0).getTitre());
        verify(packRepository).findAll();
    }

    @Test
    void createPack() {
        when(packRepository.save(any(Pack.class))).thenReturn(pack);

        Pack result = packService.createPack(packDto);

        assertNotNull(result);
        assertEquals(pack.getTitre(), result.getTitre());
        verify(packRepository).save(any(Pack.class));
    }

    @Test
    void editPack() {
        when(packRepository.findById(anyLong())).thenReturn(Optional.of(pack));
        when(packRepository.save(any(Pack.class))).thenReturn(pack);

        Pack result = packService.editPack(1L, packDto);

        assertNotNull(result);
        assertEquals(packDto.getTitre(), result.getTitre());
        verify(packRepository).findById(1L);
        verify(packRepository).save(any(Pack.class));
    }

    @Test
    void modifierReduction() {
        packService.modifierReduction(pack, 20.0);

        assertEquals(160.0, pack.getPrixTotal());
        assertEquals(20.0, pack.getReduction());
    }

    @Test
    void annulerPack() {
        ServiceNettoyage serviceNettoyage = new ServiceNettoyage();
        serviceNettoyage.setId(1L);
        List<ServiceNettoyage> services = new ArrayList<>();
        services.add(serviceNettoyage);

        when(serviceNettoyageRepository.findAllByPackId(1L)).thenReturn(services);
        // No need to mock deleteService, it's already a void method
        doNothing().when(serviceServiceImpl).deleteService(1L);

        packService.annulerPack(1L);

        verify(serviceNettoyageRepository).findAllByPackId(1L);
        verify(serviceServiceImpl).deleteService(1L);
        verify(packRepository).deleteById(1L);
    }

    @Test
    void incrementerPrixService() {
        when(packRepository.findById(anyLong())).thenReturn(Optional.of(pack));
        when(packRepository.save(any(Pack.class))).thenReturn(pack);

        Pack result = packService.IncrementerPrixService(1L, 50.0);

        assertNotNull(result);
        assertEquals(250.0, result.getPrixTotal());
        verify(packRepository).findById(1L);
        verify(packRepository).save(any(Pack.class));
    }

    @Test
    void decrementerPrixService() {
        when(packRepository.findById(anyLong())).thenReturn(Optional.of(pack));
        when(packRepository.save(any(Pack.class))).thenReturn(pack);

        Pack result = packService.DecrementerPrixService(1L, 30.0);

        assertNotNull(result);
        assertEquals(170.0, result.getPrixTotal());
        verify(packRepository).findById(1L);
        verify(packRepository).save(any(Pack.class));
    }
}
