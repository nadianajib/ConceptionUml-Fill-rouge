package com.example.CleanDar.Service;

import com.example.CleanDar.Dao.PackRepository;
import com.example.CleanDar.Dao.ReservationRepository;
import com.example.CleanDar.Dao.UtilisateurRepository;
import com.example.CleanDar.Dto.ReservationDto;
import com.example.CleanDar.model.Pack;
import com.example.CleanDar.model.Reservation;
import com.example.CleanDar.model.StatusReservation;
import com.example.CleanDar.model.Utilisateur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private UtilisateurRepository utilisateurRepository;

    @Mock
    private PackRepository packRepository;

    @InjectMocks
    private ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getReservations() {
        Utilisateur utilisateur = new Utilisateur(); // créer un utilisateur pour le test
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        when(reservationRepository.findByUtilisateur(utilisateur)).thenReturn(reservations);

        List<ReservationDto> result = reservationService.getReservations(utilisateur);

        assertEquals(1, result.size());
        verify(reservationRepository, times(1)).findByUtilisateur(utilisateur);
    }

    @Test
    void creerReservation() {
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setDateDebut("2024-10-10 10:00:00");
        reservationDto.setDateFin("2024-10-10 12:00:00");

        Reservation reservation = new Reservation();
        reservation.setDateDebut("2024-10-10 10:00:00");
        reservation.setDateFin("2024-10-10 12:00:00");
        reservation.setStatus(StatusReservation.TRAITEMENT_ENCOURS);

        when(reservationRepository.save(any(Reservation.class))).thenReturn(reservation);

        ReservationDto result = reservationService.creerReservation(reservationDto);

        assertNotNull(result);
        assertEquals("2024-10-10 10:00:00", result.getDateDebut());
        assertEquals(StatusReservation.TRAITEMENT_ENCOURS.toString(), result.getStatus());
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void getReservationsByUtilisateurId() {
        Long utilisateurId = 1L;
        List<Reservation> reservations = new ArrayList<>();
        reservations.add(new Reservation());
        when(reservationRepository.findByUtilisateurId(utilisateurId)).thenReturn(reservations);

        List<ReservationDto> result = reservationService.getReservationsByUtilisateurId(utilisateurId);

        assertEquals(1, result.size());
        verify(reservationRepository, times(1)).findByUtilisateurId(utilisateurId);
    }

    @Test
    void mettreAJourReservation() {
        Long id = 1L;
        ReservationDto reservationDto = new ReservationDto();
        reservationDto.setDateDebut("2024-10-10 10:00:00");
        reservationDto.setDateFin("2024-10-10 12:00:00");

        Reservation existingReservation = new Reservation();
        existingReservation.setId(id);
        existingReservation.setDateDebut("2024-10-10 09:00:00");
        existingReservation.setDateFin("2024-10-10 11:00:00");

        when(reservationRepository.findById(id)).thenReturn(Optional.of(existingReservation));
        when(reservationRepository.save(any(Reservation.class))).thenReturn(existingReservation);

        ReservationDto result = reservationService.mettreAJourReservation(id, reservationDto);

        assertNotNull(result);
        assertEquals("2024-10-10 10:00:00", result.getDateDebut());
        verify(reservationRepository, times(1)).findById(id);
        verify(reservationRepository, times(1)).save(any(Reservation.class));
    }

    @Test
    void getReservationById() {
        Long id = 1L;

        // Créer un utilisateur pour le test
        Utilisateur utilisateur = new Utilisateur();
        utilisateur.setId(1L); // Assigner un ID à l'utilisateur

        // Créer un pack pour le test
        Pack pack = new Pack();
        pack.setId(1L); // Assigner un ID au pack

        // Créer une réservation avec l'utilisateur et le pack
        Reservation reservation = new Reservation();
        reservation.setId(id);
        reservation.setDateDebut("2024-10-10 10:00:00");
        reservation.setDateFin("2024-10-10 12:00:00");
        reservation.setUtilisateur(utilisateur); // Associer l'utilisateur à la réservation
        reservation.setPack(pack); // Associer le pack à la réservation

        // Configurer le mock pour retourner la réservation
        when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));

        // Appeler la méthode de service
        ReservationDto result = reservationService.getReservationById(id);

        // Vérifications
        assertNotNull(result);
        assertEquals(id, result.getId());
        assertEquals(utilisateur.getId(), result.getUtilisateurId()); // Vérifiez que l'ID de l'utilisateur est correct
        assertEquals(pack.getId(), result.getPackId()); // Vérifiez que l'ID du pack est correct
        verify(reservationRepository, times(1)).findById(id);
    }

    @Test
    void annulerReservation() {
        Long id = 1L;

        reservationService.annulerReservation(id);

        verify(reservationRepository, times(1)).deleteById(id);
    }
}
