import { Component } from '@angular/core';
import { Reservation } from '../models/Reservation';
import { ReservationService } from '../Services/reservation.service';

@Component({
  selector: 'app-update-reservation',
  templateUrl: './update-reservation.component.html',
  styleUrls: ['./update-reservation.component.scss']
})
export class UpdateReservationComponent {

  reservation: Reservation = {
    id: 0,
    dateDebut: new Date(),
    dateFin: new Date(),
    packId: undefined
  };
  dateError: string | null = null;

  constructor(private reservationService: ReservationService) {}

  // Méthode pour mettre à jour la réservation
  updateReservation() {
    this.dateError = null; // Réinitialiser l'erreur

    // Validation des dates
    if (this.reservation.dateDebut >= this.reservation.dateFin) {
      this.dateError = "La date de fin doit être postérieure à la date de début.";
      return;
    }

    this.reservationService.updateReservation(this.reservation).subscribe((response: any) => {
      // Gérer la réponse après la mise à jour
      console.log("Réservation mise à jour:", response);
    }, (error: any) => {
      console.error("Erreur lors de la mise à jour de la réservation:", error);
    });
  }
}
