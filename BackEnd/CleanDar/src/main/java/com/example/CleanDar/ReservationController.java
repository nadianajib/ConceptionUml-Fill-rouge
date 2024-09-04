package com.example.CleanDar;


import com.example.CleanDar.Dto.ReservationDto;
import com.example.CleanDar.Service.ReservationService;
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
}