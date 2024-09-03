package com.example.demo3.Service;

import com.example.demo3.Dto.ReservationDto;

import java.util.List;

public interface ReservationService {
    ReservationDto createReservation(ReservationDto reservationDTO);
    List<ReservationDto> getAllReservations();
    ReservationDto updateReservation(Long id, ReservationDto reservationDTO);
    void cancelReservation(Long id);
}
