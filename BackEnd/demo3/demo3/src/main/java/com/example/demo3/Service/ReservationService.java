package com.example.demo3.Service;

import com.example.demo3.Dto.ReservationDto;
import com.example.demo3.model.Reservation;
import java.util.List;

public interface ReservationService {

    ReservationDto creerReservation(ReservationDto reservationDto);
    List<ReservationDto> getReservationsByUtilisateurId(Long utilisateurId);
    ReservationDto mettreAJourReservation(Long id, ReservationDto reservationDto);
    void annulerReservation(Long id);


}