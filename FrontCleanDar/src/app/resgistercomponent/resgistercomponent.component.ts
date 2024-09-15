import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { RegistrerService } from '../Services/registrer.service';
@Component({
    selector: 'app-register',
    templateUrl: './resgistercomponent.component.html',
    styleUrls: ['./resgistercomponent.component.scss']
    
  })
  
  
export class RegisterComponent implements OnInit {
  registerForm: FormGroup;
  errorMessage: string = '';
  successMessage: string = '';

  constructor(
    private fb: FormBuilder,
    private registrerService: RegistrerService
  ) {
    this.registerForm = this.fb.group({
      nom: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    // Initialisation si nécessaire
  }

  onSubmit() {
    if (this.registerForm.valid) {
      const signRequest = this.registerForm.value;
      this.registrerService.registrer(signRequest).subscribe(
        response => {
          this.successMessage = 'Inscription réussie!';
          this.errorMessage = '';
          this.registerForm.reset();
        },
        error => {
          this.errorMessage = `Erreur lors de l'inscription: ${error.message || 'Erreur inconnue'}`;
          this.successMessage = '';
        }
      );
    } else {
      this.errorMessage = 'Veuillez remplir tous les champs correctement.';
    }
  }
}
