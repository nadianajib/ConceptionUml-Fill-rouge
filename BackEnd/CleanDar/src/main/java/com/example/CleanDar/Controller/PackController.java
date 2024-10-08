package com.example.CleanDar.Controller;


import com.example.CleanDar.Dto.PackDto;
import com.example.CleanDar.Service.PackService;
import com.example.CleanDar.model.Pack;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/v1/packs")
public class PackController {

    @Autowired
    private PackService packService;

//    @PostMapping("/creer")
//    public ResponseEntity<Pack> creerPack(@RequestBody PackDto request) {
//        try {
//            List<Long> serviceIds = request.getServicesIds();
//            Double reduction = request.getReduction();
//            Pack nouveauPack = packService.creerPack(serviceIds, reduction);
//
//            // Retourner le pack nouvellement créé avec le statut HTTP 201 Created
//            return ResponseEntity.status(HttpStatus.CREATED).body(nouveauPack);
//        } catch (Exception e) {
//            // Gérer les erreurs éventuelles
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
//@PostMapping("/creer")
//public ResponseEntity<Pack> createPack(@RequestBody PackDto packDto) {
//    Pack createdPack = packService.creerPack(packDto);
//    return ResponseEntity.ok(createdPack);
//}
//@PostMapping("/creer")
//public ResponseEntity<Pack> creerPack(@RequestBody PackDto packDto) {
//    Pack pack = packService.creerPack(packDto);
//    return new ResponseEntity<>(pack, HttpStatus.CREATED);
//}
    @GetMapping("/all")
    public ResponseEntity<List<PackDto>> getAllPacks() {
        List<PackDto> packs = packService.getAllPacks();
        return ResponseEntity.ok(packs);
    }
    @PostMapping("/add")
    public ResponseEntity<Pack> createPack(@RequestBody PackDto packDto) {
        Pack createdPack = packService.createPack(packDto);
        return ResponseEntity.ok(createdPack);
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