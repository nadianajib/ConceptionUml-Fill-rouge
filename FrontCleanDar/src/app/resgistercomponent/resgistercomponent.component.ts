import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'; // Pour rediriger après une inscription réussie
import { RegistrerService } from '../Services/registrer.service';

@Component({
  selector: 'app-registrercomponent',
  templateUrl: './resgistercomponent.component.html',
  styleUrls: ['./resgistercomponent.component.scss']
})
export class RegistrercomponentComponent implements OnInit {
  registerForm!: FormGroup;
  isSubmitted = false;

  constructor(
    private fb: FormBuilder,
    private service: RegistrerService,
    private router: Router // Pour la navigation après inscription
  ) {}

  ngOnInit(): void {
    this.initForm();
  }

  // Initialise le formulaire avec des validations
  private initForm(): void {
    this.registerForm = this.fb.group({
      nom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(3)]]
    });
  }

  // Méthode appelée lors de la soumission du formulaire
  onSubmit(): void {
    this.isSubmitted = true;

    if (this.registerForm.invalid) {
      return; // Stop si le formulaire n'est pas valide
    }

    // Appel du service pour enregistrer l'utilisateur
    this.service.registrer(this.registerForm.value).subscribe({
      next: () => {
        console.log('Inscription réussie');
        console.log(this.registerForm.value);
        // Redirection vers une autre page, par exemple la page de connexion
        this.router.navigate(['/login']);
      },
      error: (err) => {
        console.error('Erreur lors de l\'inscription', err);
        // Gérer l'erreur ici (afficher un message d'erreur, etc.)
      }
    });
  }
  
}
