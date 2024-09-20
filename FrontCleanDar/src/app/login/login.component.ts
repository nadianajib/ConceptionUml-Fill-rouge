import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { Jwt } from '../models/Jwt';
import { RegistrerService } from '../Services/registrer.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.scss']
})
export class LoginComponent implements OnInit {

  loginForm!: FormGroup;
  
  constructor(
    private service: RegistrerService,
    private fb: FormBuilder,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.loginForm = this.fb.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required]],
    });
  }

  submitForm(): void {
    console.log(this.loginForm.value);
    this.service.login(this.loginForm.value).subscribe(
      (response: Jwt) => {
   
        const jwToken = response.token;
        const userRole = response.role; // récupérer le rôle

        // Stocker le token et le rôle dans le localStorage
        localStorage.setItem('jwt', jwToken);
        localStorage.setItem('role', userRole);

        // Rediriger l'utilisateur selon son rôle
        if (userRole === 'Admin') {
          this.router.navigateByUrl('/admin-dashboard'); // Tableau de bord admin
        } else if (userRole === 'User') {
          this.router.navigateByUrl('/add-reservation'); // Vue utilisateur
        }
      }
    );
  }
}
