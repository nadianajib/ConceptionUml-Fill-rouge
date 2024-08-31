package com.example.demo3.Controller;

import com.example.demo3.Dto.ReservationDto;
import com.example.demo3.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("/add")
    public ResponseEntity<ReservationDto> addReservation(@RequestBody ReservationDto reservationDto) {
        try {
            ReservationDto createdReservation = reservationService.creerReservation(reservationDto);
            return ResponseEntity.ok(createdReservation);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);  // Retourne un 400 avec plus de détails si besoin
        }
    }
    @GetMapping("/user/{utilisateurId}")
    public ResponseEntity<List<ReservationDto>> getReservationsByUtilisateurId(@PathVariable Long utilisateurId) {
        try {
            List<ReservationDto> reservations = reservationService.getReservationsByUtilisateurId(utilisateurId);
            return ResponseEntity.ok(reservations);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);  // Retourne un 400 avec plus de détails si besoin
        }
    }
}

