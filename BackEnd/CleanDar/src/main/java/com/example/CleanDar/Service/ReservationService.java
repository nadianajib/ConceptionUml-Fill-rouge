package com.example.CleanDar.Service;

import com.example.CleanDar.Dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    ReservationDto creerReservation(ReservationDto reservationDto);
    List<ReservationDto> getReservationsByUtilisateurId(Long utilisateurId);
    ReservationDto mettreAJourReservation(Long id, ReservationDto reservationDto);


}
