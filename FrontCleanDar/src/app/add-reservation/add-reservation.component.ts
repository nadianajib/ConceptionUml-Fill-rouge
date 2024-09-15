import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReservationService } from '../Services/reservation.service';
import { Reservation } from '../models/Reservation';

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html'
})
export class AddReservationComponent implements OnInit {
  reservationForm: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private reservationService: ReservationService
  ) {
    this.reservationForm = this.fb.group({
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      packId: [null, [Validators.required, Validators.min(1)]]
    });
  }

  ngOnInit() {
    // Vous pouvez initialiser des données ici si nécessaire
  }

  onSubmit() {
    if (this.reservationForm.valid) {
      const reservation: Reservation = this.reservationForm.value;
      this.reservationService.addReservation(reservation).subscribe(
        (response) => {
          console.log('Réservation ajoutée avec succès:', response);
          this.successMessage = 'Réservation ajoutée avec succès!';
          this.errorMessage = '';
          this.reservationForm.reset();
        },
        (error) => {
          console.error('Erreur complète:', error);
          this.errorMessage = `Erreur lors de l'ajout de la réservation: ${error.message || 'Erreur inconnue'}`;
          this.successMessage = '';
        }
      );
    } else {
      this.errorMessage = 'Veuillez remplir tous les champs correctement.';
    }
  }
}
