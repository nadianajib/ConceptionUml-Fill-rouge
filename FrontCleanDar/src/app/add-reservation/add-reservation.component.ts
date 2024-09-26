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
  reservationForm!: FormGroup; // Déclaration du formulaire

  constructor(
    private fb: FormBuilder,
    private reservationService: ReservationService,
    protected router: Router
  ) {}

  ngOnInit(): void {
    // Initialisation du formulaire avec des contrôles et des validateurs
    this.reservationForm = this.fb.group({
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      packId: ['', Validators.required]
    });

    // Ajouter la validation personnalisée pour la vérification des dates
    this.reservationForm.setValidators(this.dateMismatchValidator);
  }

  // Validator personnalisé pour vérifier si la date de début est avant la date de fin
  dateMismatchValidator(control: AbstractControl) {
    const formGroup = control as FormGroup;
    const dateDebut = formGroup.get('dateDebut')?.value;
    const dateFin = formGroup.get('dateFin')?.value;

    if (dateDebut && dateFin && new Date(dateDebut) >= new Date(dateFin)) {
      return { dateMismatch: true }; // Erreur de validation
    }
    return null; // Validation réussie
  }

  // Fonction appelée lors de la soumission du formulaire
  onSubmit(): void {
    if (this.reservationForm.valid) {
      const reservationData = this.reservationForm.value; // Récupération des données du formulaire
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
