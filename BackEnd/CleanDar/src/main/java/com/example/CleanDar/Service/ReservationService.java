package com.example.CleanDar.Service;

import com.example.CleanDar.Dto.ReservationDto;
import com.example.CleanDar.model.Utilisateur;

import java.util.List;

public interface ReservationService {
    ReservationDto creerReservation(ReservationDto reservationDto);
    List<ReservationDto> getReservationsByUtilisateurId(Long utilisateurId);
    ReservationDto mettreAJourReservation(Long id, ReservationDto reservationDto);

    ReservationDto getReservationById(Long id);

    void annulerReservation(Long id);
    List<ReservationDto> getReservations(Utilisateur utilisateur);


}



