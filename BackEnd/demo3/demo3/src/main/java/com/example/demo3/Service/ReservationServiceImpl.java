package com.example.demo3.Service;


import com.example.demo3.Dao.ReservationRepository;
import com.example.demo3.Dao.UtilisateurRepository;
import com.example.demo3.Dto.ReservationDto;
import com.example.demo3.model.Pack;
import com.example.demo3.model.Reservation;
import com.example.demo3.model.Utilisateur;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReservationServiceImpl {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UtilisateurRepository utilisateurRepository;

    @Autowired
    private PackRepository packRepository;

    // Créer une nouvelle réservation
    public ReservationDto createReservation(ReservationDto reservationDto) {
        Utilisateur utilisateur = utilisateurRepository.findById(reservationDto.getUtilisateurId())
                .orElseThrow(() -> new RuntimeException("Utilisateur non trouvé"));

        Pack pack = packRepository.findById(reservationDto.getPackId())
                .orElseThrow(() -> new RuntimeException("Pack non trouvé"));

        Reservation reservation = new Reservation();
        reservation.setDateDebut(reservationDto.getDateDebut());
        reservation.setDateFin(reservationDto.getDateFin());
        reservation.setUtilisateur(utilisateur);
        reservation.setPack(pack);

        Reservation savedReservation = reservationRepository.save(reservation);

        // Retourner le DTO après le mapping manuel
        return reservationToDto(savedReservation);
    }