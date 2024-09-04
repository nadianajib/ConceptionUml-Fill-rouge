package com.example.CleanDar.Service;


import com.example.CleanDar.Dao.PackRepository;
import com.example.CleanDar.Dao.ReservationRepository;
import com.example.CleanDar.Dao.UtilisateurRepository;
import com.example.CleanDar.Dto.ReservationDto;
import com.example.CleanDar.model.Pack;
import com.example.CleanDar.model.Reservation;
import com.example.CleanDar.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ReservationServiceImpl implements ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PackRepository packRepository;

    @Override
    public ReservationDto creerReservation(ReservationDto reservationDto) {
        Reservation reservation = dtoToEntity(reservationDto);
        Reservation savedReservation = reservationRepository.save(reservation);
        return entityToDto(savedReservation);
    }

    private Reservation dtoToEntity(ReservationDto reservationDto) {
        Reservation reservation = new Reservation();
        reservation.setId(reservationDto.getId());
        reservation.setDateDebut(reservationDto.getDateDebut());
        reservation.setDateFin(reservationDto.getDateFin());

        // Associer l'utilisateur si disponible
        if (reservationDto.getUtilisateurId() != null) {
            Optional<Utilisateur> utilisateur = utilisateurRepository.findById(reservationDto.getUtilisateurId());
            utilisateur.ifPresent(reservation::setUtilisateur);
        }

        // Associer le pack si disponible
        if (reservationDto.getPackId() != null) {
            Optional<Pack> pack = packRepository.findById(reservationDto.getPackId());
            pack.ifPresent(reservation::setPack);
        }

        return reservation;
    }

    private ReservationDto entityToDto(Reservation reservation) {
        ReservationDto dto = new ReservationDto();
        dto.setId(reservation.getId());
        dto.setDateDebut(reservation.getDateDebut());
        dto.setDateFin(reservation.getDateFin());

        // Ajouter l'ID de l'utilisateur si disponible
        if (reservation.getUtilisateur() != null) {
            dto.setUtilisateurId(reservation.getUtilisateur().getId());
        }

        // Ajouter l'ID du pack si disponible
        if (reservation.getPack() != null) {
            dto.setPackId(reservation.getPack().getId());
        }

        return dto;
    }
    @Override
    public List<ReservationDto> getReservationsByUtilisateurId(Long utilisateurId) {
        List<Reservation> reservations = reservationRepository.findByUtilisateurId(utilisateurId);
        List<ReservationDto> reservationDtos = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationDtos.add(entityToDto(reservation));
        }

        return reservationDtos;
    }
    @Override
    public ReservationDto mettreAJourReservation(Long id, ReservationDto reservationDto) {
        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isPresent()) {
            Reservation reservation = optionalReservation.get();
            reservation.setDateDebut(reservationDto.getDateDebut());
            reservation.setDateFin(reservationDto.getDateFin());

            // Associer l'utilisateur si disponible
            if (reservationDto.getUtilisateurId() != null) {
                Optional<Utilisateur> utilisateur = utilisateurRepository.findById(reservationDto.getUtilisateurId());
                utilisateur.ifPresent(reservation::setUtilisateur);
            }

            // Associer le pack si disponible
            if (reservationDto.getPackId() != null) {
                Optional<Pack> pack = packRepository.findById(reservationDto.getPackId());
                pack.ifPresent(reservation::setPack);
            }

            Reservation updatedReservation = reservationRepository.save(reservation);
            return entityToDto(updatedReservation);
        } else {
            // Gérer le cas où la réservation n'existe pas
            return null;  // Ou lancer une exception
        }
    }

}