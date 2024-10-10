import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Reservation } from '../models/Reservation';
import { ReservationService } from '../Services/reservation.service';

@Component({
  selector: 'app-update-reservation',
  templateUrl: './update-reservation.component.html',
  styleUrls: ['./update-reservation.component.scss']
})
export class UpdateReservationComponent implements OnInit {
  reservation: Reservation = {
    id: 0,
    dateDebut: '',
    dateFin: '',
    packId: 0,
    utilisateurId: 0
  };
  reservationId: number | undefined;

  constructor(
    private reservationService: ReservationService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    const id = this.route.snapshot.paramMap.get('id');
    if (id) {
      this.reservationId = Number(id);
      this.getReservationById(this.reservationId);
    } else {
      console.error('Reservation ID is undefined');
      this.router.navigate(['/reservations']); // Redirigez l'utilisateur si l'ID n'est pas défini
    }
  }

  getReservationById(id: number): void {
    this.reservationService.getReservationById(id).subscribe(
      (data: Reservation) => {
        this.reservation = data;
      },
      (error) => {
        console.error('Error fetching reservation:', error);
      }
    );
  }

  updateReservation(): void {
    // Vérifiez que reservationId n'est pas undefined avant la mise à jour
    if (this.reservationId) {
      this.reservationService.updateReservation(this.reservationId, this.reservation).subscribe(
        () => {
          alert('Reservation updated successfully!');
          this.router.navigate(['/reservations']); // Rediriger vers la liste des réservations
        },
        (error) => {
          console.error('Error updating reservation:', error);
        }
      );
    } else {
      console.error('Reservation ID is undefined during update');
    }
  }
}
