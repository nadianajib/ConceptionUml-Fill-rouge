import { Component, Input } from '@angular/core';
import { RegistrerService } from '../Services/registrer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-navbar',
  templateUrl: './navbar.component.html',
  styleUrls: ['./navbar.component.scss']
})
export class NavbarComponent {

  constructor(
    private Service: RegistrerService,
    private router: Router
  ){

  }
  isLoggedIn(): boolean {
    return !!localStorage.getItem('jwt');
  }

  logout() {
    this.Service.logout();
  }

  login() {
    this.router.navigate(['/login']);
  }

  signup() {
    this.router.navigate(['/register']);
  }
}
