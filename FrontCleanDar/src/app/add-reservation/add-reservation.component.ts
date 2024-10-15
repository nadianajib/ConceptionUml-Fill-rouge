import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ReservationService } from '../Services/reservation.service';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrls: ['./add-reservation.component.scss']
})
export class AddReservationComponent implements OnInit {
  reservationForm!: FormGroup;
  packId!: number;

  constructor(
    private fb: FormBuilder,
    private reservationService: ReservationService,
    protected router: Router,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    this.reservationForm = this.fb.group(
      {
        dateDebut: ['', Validators.required],
        dateFin: ['', Validators.required],
        packId: ['', Validators.required]
      },
      { validators: this.dateMismatchValidator } // Appliquer le validateur au groupe de formulaire
    );

    // Récupérer l'ID du pack depuis les paramètres de route
    this.packId = +this.route.snapshot.paramMap.get('packId')!;
    console.log('Pack ID:', this.packId); 

    // Remplir le champ packId dans le formulaire
    this.reservationForm.patchValue({ packId: this.packId });
  }

  // Validator personnalisé pour vérifier si la date de début est avant la date de fin
  dateMismatchValidator(formGroup: FormGroup) {
    const dateDebut = formGroup.get('dateDebut')?.value;
    const dateFin = formGroup.get('dateFin')?.value;

    if (dateDebut && dateFin && new Date(dateDebut) >= new Date(dateFin)) {
      return { dateMismatch: true }; // Si la date de début >= date de fin, retourne une erreur
    }
    return null; // Sinon, tout va bien
  }

  // Fonction appelée lors de la soumission du formulaire
  onSubmit(): void {
    if (this.reservationForm.valid) {
      const reservationData = this.reservationForm.value; // Récupérer les données du formulaire
      this.reservationService.addReservation(reservationData).subscribe(
        response => {
          console.log('Réservation ajoutée avec succès', response);
          this.router.navigate(['/list-reservation']); // Redirige vers la liste des réservations
        },
        error => {
          console.error('Erreur lors de l\'ajout de la réservation', error);
        }
      );
    }
  }
}
