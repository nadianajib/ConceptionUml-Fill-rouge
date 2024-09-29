import { Component, OnInit } from '@angular/core';
import { Reservation } from '../models/Reservation';
import { ReservationService } from '../Services/reservation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-list-reservation',
  templateUrl: './list-reservation.component.html',
  styleUrls: ['./list-reservation.component.scss']
})
export class ListReservationComponent implements OnInit {
  reservations: Reservation[] = [];
  menuActive: boolean = false; // Ajouter une propriété pour gérer l'état du menu

  constructor(private reservationService: ReservationService, private router: Router) {}

  ngOnInit(): void {
    this.loadMyReservations();
  }

  loadMyReservations(): void {
    this.reservationService.getMyReservations().subscribe({
      next: (data) => {
        console.log('Données récupérées:', data);
        this.reservations = data;
      },
      error: (error) => {
        console.error('Erreur lors de la récupération des réservations', error);
      }
    });
  }

  toggleMenu(): void {
    this.menuActive = !this.menuActive; // Alterner l'état du menu
  }

  // Méthode pour modifier la réservation
  editReservation(id: number): void {
    this.router.navigate(['/update-reservation', id]);
  }

  // Méthode pour supprimer la réservation
  deleteReservation(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cette réservation ?')) {
      this.reservationService.deleteReservation(id).subscribe({
        next: () => {
          console.log('Réservation supprimée avec succès');
          this.loadMyReservations(); // Recharge les réservations après la suppression
        },
        error: (error) => {
          console.error('Erreur lors de la suppression de la réservation', error);
        }
      });
    }
  }
}