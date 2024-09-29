import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { ReservationService } from '../Services/reservation.service';
import { Reservation } from '../models/Reservation';

@Component({
  selector: 'app-update-reservation',
  templateUrl: './update-reservation.component.html',
  styleUrls: ['./update-reservation.component.scss']
})
export class UpdateReservationComponent implements OnInit {
  reservationId!: number; // ID de la réservation à mettre à jour
  reservation!: Reservation; // Détails de la réservation
  loading: boolean = true; // État de chargement
  public router: Router; // Changer à public

  constructor(
    private reservationService: ReservationService,
    private route: ActivatedRoute,
    router: Router // Passer le router dans le constructeur
  ) {
    this.router = router; // Initialiser le router
  }

  ngOnInit(): void {
    // Récupérer l'ID de la réservation à partir de l'URL
    this.reservationId = Number(this.route.snapshot.paramMap.get('id'));
    this.loadReservationDetails();
  }

  // Charger les détails de la réservation existante
  loadReservationDetails(): void {
    this.reservationService.getReservationById(this.reservationId).subscribe({
        next: (data) => {
            this.reservation = data;
            this.loading = false;
        },
        error: (error) => {
            console.error('Erreur lors de la récupération des détails:', error);
            this.loading = false; // Mettre à jour l'état de chargement même en cas d'erreur
            alert('Erreur lors du chargement des détails de la réservation. Veuillez vérifier la console pour plus d\'informations.');
        }
    });
}

  updateReservation(): void {
    this.reservationService.updateReservation(this.reservationId, this.reservation).subscribe({
      next: () => {
        // Rediriger ou afficher un message de succès
        this.router.navigate(['/list-reservation']);
      },
      error: (error) => {
        console.error('Erreur lors de la mise à jour de la réservation:', error);
      }
    });
  }
}
