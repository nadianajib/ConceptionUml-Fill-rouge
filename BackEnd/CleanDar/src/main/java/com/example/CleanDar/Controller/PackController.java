package com.example.CleanDar.Controller;


import com.example.CleanDar.Dto.PackDto;
import com.example.CleanDar.Service.PackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/packs/Admin")
public class PackController {

    @Autowired
    private PackService packService;

    @PostMapping("/add")
    public ResponseEntity<PackDto> addPack(@RequestBody PackDto packDTO) {
        PackDto createdPack = packService.createPack(packDTO);
        return ResponseEntity.ok(createdPack);
    }
    @GetMapping("/all")
    public ResponseEntity<List<PackDto>> getAllPacks() {
        List<PackDto> packs = packService.getAllPacks();
        return ResponseEntity.ok(packs);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> annulerPack(@PathVariable Long id) {
        try {
            packService.annulerPack(id); // Appelle le service pour annuler la réservation
            return ResponseEntity.noContent().build();  // Retourne un 204 No Content si la suppression est réussie
        } catch (Exception e) {
            return ResponseEntity.notFound().build();  // Retourne un 404 Not Found si la réservation n'est pas trouvée
        }
    }
}