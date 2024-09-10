package com.example.CleanDar.Controller;


import com.example.CleanDar.Dao.UtilisateurRepository;
import com.example.CleanDar.Dto.ReservationDto;
import com.example.CleanDar.Service.ReservationService;
import com.example.CleanDar.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api/v1/auth/User/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private UtilisateurRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<ReservationDto> addReservation(@RequestBody ReservationDto reservationDto, @AuthenticationPrincipal Utilisateur user) {
        try {
            Utilisateur utilisateurVerifie = userRepository
                    .findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur introuvable"));

            // add utilisateur ReservationDto
            reservationDto.setUtilisateurId(utilisateurVerifie.getId());

            ReservationDto createdReservation = reservationService.creerReservation(reservationDto);
            return ResponseEntity.ok(createdReservation);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/my-reservations")
    public ResponseEntity<List<ReservationDto>> getMyReservations(@AuthenticationPrincipal Utilisateur user) {
        try {
            Utilisateur utilisateurVerifie = userRepository
                    .findById(user.getId())
                    .orElseThrow(() -> new RuntimeException("Utilisateur malgish f database"));

            List<ReservationDto> userReservations = reservationService.getReservations(utilisateurVerifie);
            return ResponseEntity.ok(userReservations);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

//    @GetMapping("/User/{utilisateurId}")
//    public ResponseEntity<List<ReservationDto>> getReservationsByUtilisateurId(@PathVariable Long utilisateurId) {
//        try {
//            List<ReservationDto> reservations = reservationService.getReservationsByUtilisateurId(utilisateurId);
//            return ResponseEntity.ok(reservations);
//        } catch (Exception e) {
//            return ResponseEntity.badRequest().body(null);  // Retourne un 400 avec plus de détails si besoin
//        }
//    }
//@GetMapping("/all")
//public ResponseEntity<List<ReservationDto>> getAllReservations() {
//    try {
//        List<ReservationDto> reservations = reservationService.getReservations();  // Appelle la méthode du service
//        return ResponseEntity.ok(reservations);  // Retourne les réservations sous forme de DTOs
//    } catch (Exception e) {
//        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);  // Retourne un code 500 en cas d'erreur
//    }
//}



    @PutMapping("/update/{id}")
    public ResponseEntity<ReservationDto> mettreAJourReservation(@PathVariable Long id, @RequestBody ReservationDto reservationDto) {
        try {
            ReservationDto updatedReservation = reservationService.mettreAJourReservation(id, reservationDto);
            if (updatedReservation != null) {
                return ResponseEntity.ok(updatedReservation);
            } else {
                return ResponseEntity.notFound().build();  // Réservation non trouvée
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);  // Retourne un 400 avec plus de détails si besoin
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> annulerReservation(@PathVariable Long id) {
        try {
            reservationService.annulerReservation(id); // Appelle le service pour annuler la réservation
            return ResponseEntity.noContent().build();  // Retourne un 204 No Content si la suppression est réussie
        } catch (Exception e) {
            return ResponseEntity.notFound().build();  // Retourne un 404 Not Found si la réservation n'est pas trouvée
        }
    }

}
