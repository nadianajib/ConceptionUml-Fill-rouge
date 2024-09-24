import { Component, OnInit } from '@angular/core';
import { Reservation } from '../models/Reservation';
import { ReservationService } from '../Services/reservation.service';

@Component({
  selector: 'app-list-reservation',
  templateUrl: './list-reservation.component.html',
  styleUrls: ['./list-reservation.component.scss']
})
export class ListReservationComponent implements OnInit {
  reservations: Reservation[] = [];

  constructor(private reservationService: ReservationService) {}

  ngOnInit(): void {
    this.loadMyReservations();
  }

  loadMyReservations(): void {
    this.reservationService.getMyReservations().subscribe({
      next: (data) => {
        console.log('Données récupérées:', data); // شوف واش packId كاين
        this.reservations = data;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des réservations', error);
      }
    });
  }
  
  
  
  
}
