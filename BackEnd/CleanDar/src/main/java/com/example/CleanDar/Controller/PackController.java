package com.example.CleanDar.Controller;


import com.example.CleanDar.Dto.PackDto;
import com.example.CleanDar.Service.PackService;
import com.example.CleanDar.model.Pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/packs/Admin")
public class PackController {

    @Autowired
    private PackService packService;

    @PostMapping("/creer")
    public ResponseEntity<Pack> creerPack(@RequestBody Map<String, List<Long>> request) {
        try {
            List<Long> serviceIds = request.get("serviceIds");
            Pack nouveauPack = packService.creerPack(serviceIds);

            // Retourner le pack nouvellement créé avec le statut HTTP 201 Created
            return ResponseEntity.ok(nouveauPack);
        } catch (Exception e) {
            // Gérer les erreurs éventuelles
            return ResponseEntity.status(500).body(null);
        }
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