import { Component, OnInit } from '@angular/core';
import { Reservation } from '../models/Reservation';
import { ReservationService } from '../Services/reservation.service';
import { Router } from '@angular/router'; // Pour la redirection après modification

@Component({
  selector: 'app-list-reservation',
  templateUrl: './list-reservation.component.html',
  styleUrls: ['./list-reservation.component.scss']
})
export class ListReservationComponent implements OnInit {
  reservations: Reservation[] = [];

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

  // Méthode pour modifier la réservation
  editReservation(id: number): void {
    this.router.navigate(['/edit-reservation', id]); // Rediriger vers le composant de modification
  }

  // Méthode pour supprimer la réservation
  deleteReservation(id: number): void {
    if (confirm('Voulez-vous vraiment supprimer cette réservation ?')) {
      this.reservationService.deleteReservation(id).subscribe({
        next: () => {
          console.log('Réservation supprimée avec succès');
          this.loadMyReservations(); // Recharger la liste après suppression
        },
        error: (error) => {
          console.error('Erreur lors de la suppression de la réservation', error);
        }
      });
    }
  }
}
