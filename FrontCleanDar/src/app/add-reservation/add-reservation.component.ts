import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ReservationService } from '../Services/reservation.service';
import { Reservation } from '../models/Reservation';
import { Route, Router } from '@angular/router';

@Component({
  selector: 'app-add-reservation',
  templateUrl: './add-reservation.component.html'
})
export class AddReservationComponent implements OnInit {
  reservationForm!: FormGroup;

  constructor(
    private fb: FormBuilder,
    private reservationService: ReservationService,
    private router : Router
  ) { }

  ngOnInit(): void {
    this.reservationForm = this.fb.group({
      dateDebut: ['', Validators.required],
      dateFin: ['', Validators.required],
      packId: ['', Validators.required]          // ID du pack
    });
  }

  onSubmit() {
    if (this.reservationForm.valid) {
      const reservationData: Reservation = this.reservationForm.value;
      console.log(reservationData)
      this.reservationService.addReservation(reservationData).subscribe({
        next: (response) => {
          console.log('Réservation ajoutée avec succès', response);
          this.router.navigateByUrl('/dashboard'); 
        },
        error: (err) => {
          console.error('Erreur lors de l\'ajout de la réservation', err);
        }
      });
    }
  }
}