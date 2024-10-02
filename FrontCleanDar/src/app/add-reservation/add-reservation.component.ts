import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators, AbstractControl } from '@angular/forms';
import { ReservationService } from '../Services/reservation.service';
import { Router, ActivatedRoute } from '@angular/router'; // Assure-toi que ActivatedRoute est importé

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html',
  styleUrls: ['./add-reservation.component.scss']
})
export class AddReservationComponent implements OnInit {
  reservationForm!: FormGroup; // Déclaration du formulaire
  packId!: number; // Déclaration de packId

  constructor(
    private fb: FormBuilder,
    private reservationService: ReservationService,
    protected router: Router,
    private route: ActivatedRoute // Injection d'ActivatedRoute
  ) {}

  ngOnInit(): void {
    // Initialisation du formulaire avec des contrôles et des validateurs
    this.reservationForm = this.fb.group({
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      packId: ['', Validators.required]
    });

    // Récupérer l'ID du pack depuis les paramètres de route
    this.packId = +this.route.snapshot.paramMap.get('packId')!;
    console.log('Pack ID:', this.packId); 

    // Remplir le champ packId dans le formulaire
    this.reservationForm.patchValue({ packId: this.packId });
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
          this.router.navigate(['/list-reservation']); // Redirige vers la liste des réservations
        },
        error => {
          console.error('Erreur lors de l\'ajout de la réservation', error);
        }
      );
    }
  }
}
