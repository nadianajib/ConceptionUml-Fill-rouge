// import { Component, OnInit } from '@angular/core';
// import { ActivatedRoute, Router } from '@angular/router';
// import { ReservationService } from '../Services/reservation.service';
// import { Reservation } from '../models/Reservation';

// @Component({
//   selector: 'app-update-reservation',
//   templateUrl: './update-reservation.component.html',
//   styleUrls: ['./update-reservation.component.scss']
// })
// export class UpdateReservationComponent implements OnInit {
//   reservationId!: number; // ID de la réservation à mettre à jour
//   reservation!: Reservation; // Détails de la réservation
//   loading: boolean = true; // État de chargement

//   constructor(
//     private reservationService: ReservationService,
//     private route: ActivatedRoute,
//     public router: Router // Router injecté ici
//   ) {}

//   ngOnInit(): void {
//     // Récupérer l'ID de la réservation à partir de l'URL
//     this.reservationId = Number(this.route.snapshot.paramMap.get('id'));
//     console.log('ID de la réservation récupéré:', this.reservationId); // Vérifiez ce qui est récupéré
//     this.loadReservationDetails();
//   }

//   // Charger les détails de la réservation existante
//   loadReservationDetails(): void {
//     if (this.reservationId <= 0) {
//       alert('ID de réservation invalide.');
//       this.loading = false;
//       return;
//     }
    
//     console.log('Récupération des détails pour l\'ID:', this.reservationId);
//     this.reservationService.getReservationById(this.reservationId).subscribe({
//         next: (data) => {
//             console.log('Détails de la réservation:', data);
//             this.reservation = data;
//             this.loading = false;
//         },
//         error: (error) => {
//             console.error('Erreur lors de la récupération des détails:', error);
//             this.loading = false;
//             alert('Erreur lors du chargement des détails de la réservation.');
//         }
//     });
//   }

//   // Méthode pour mettre à jour la réservation
//   updateReservation(): void {
//     this.reservationService.updateReservation(this.reservationId, this.reservation).subscribe({
//       next: () => {
//         // Rediriger ou afficher un message de succès
//         alert('Réservation mise à jour avec succès !');
//         this.router.navigate(['/list-reservation']); // Redirection après la mise à jour
//       },
//       error: (error) => {
//         console.error('Erreur lors de la mise à jour de la réservation:', error);
//         alert('Erreur lors de la mise à jour de la réservation.');
//       }
//     });
//   }
// }
