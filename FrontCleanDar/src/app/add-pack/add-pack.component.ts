import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PackService } from '../Services/pack.service';

@Component({
  selector: 'app-add-pack',
  templateUrl: './add-pack.component.html',
  styleUrls: ['./add-pack.component.scss']
})
export class AddPackComponent {
  packForm: FormGroup;
  submitted = false;

  constructor(private formBuilder: FormBuilder, private packService: PackService, private router: Router) {
    // Initialisation du formulaire
    this.packForm = this.formBuilder.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      prixTotal: ['', [Validators.required, Validators.min(0)]],
      reduction: ['', [Validators.required, Validators.min(0)]],
    });
  }

  // Getter pour accéder facilement aux champs du formulaire
  get f() { return this.packForm.controls; }

  onSubmit() {
    this.submitted = true;

    // Stop si le formulaire est invalide
    if (this.packForm.invalid) {
      return;
    }

    // Appel du service pour créer un nouveau pack
    this.packService.createPack(this.packForm.value).subscribe({
      next: (response) => {
        console.log('Pack créé avec succès', response);
        // Rediriger ou faire quelque chose après la création
        this.router.navigate(['/list-packs']);
      },
      error: (error) => {
        console.error('Erreur lors de la création du pack', error);
      }
    });
  }
}
