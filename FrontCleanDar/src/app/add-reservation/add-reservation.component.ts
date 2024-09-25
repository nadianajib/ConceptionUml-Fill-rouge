import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ReservationService } from '../Services/reservation.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrls: ['./add-reservation.component.scss']
})
export class AddReservationComponent implements OnInit {
  reservationForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private reservationService: ReservationService,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.reservationForm = this.fb.group({
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      packId: ['', Validators.required]
    });

    // Ajouter la validation personnalisée pour la vérification des dates
    this.reservationForm.setValidators(this.dateMismatchValidator);
  }

  dateMismatchValidator(control: AbstractControl) {
    const formGroup = control as FormGroup;
    const dateDebut = formGroup.get('dateDebut')?.value;
    const dateFin = formGroup.get('dateFin')?.value;

    if (dateDebut && dateFin && new Date(dateDebut) >= new Date(dateFin)) {
      return { dateMismatch: true };
    }
    return null;
  }

  onSubmit(): void {
    if (this.reservationForm.valid) {
      const reservationData = this.reservationForm.value;
      this.reservationService.addReservation(reservationData).subscribe(
        response => {
          console.log('Réservation ajoutée avec succès', response);
          this.router.navigate(['/dashboard']); // Redirige vers la liste des réservations
        },
        error => {
          console.error('Erreur lors de l\'ajout de la réservation', error);
        }
      );
    }
  }
}
