import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable, tap } from 'rxjs';
import { Jwt } from '../models/Jwt';
import { Router } from '@angular/router';
import { JwtHelperService } from '@auth0/angular-jwt';
import { RegisterData } from '../models/RegisterData';
import { LoginData } from '../models/LoginData';



@Injectable({
  providedIn: 'root'
})
export class RegistrerService {
  private readonly TOKEN_KEY = 'jwt';
  private readonly ROLE_KEY = 'role';

  private BASE_URL = "http://localhost:9095/api/v1/auth"; 

  constructor(
    private http: HttpClient,
    private router: Router,
    private jwtHelper: JwtHelperService
  ) { }

  register(registerdata: RegisterData): Observable<Jwt> {
    return this.http.post<Jwt>(`${this.BASE_URL}/register`, registerdata);
  }

  login(logindata: LoginData): Observable<Jwt> {
    return this.http.post<Jwt>(`${this.BASE_URL}/authenticate`, logindata).pipe(
      tap((response: Jwt) => {
        if (response && response.token) {
          localStorage.setItem(this.TOKEN_KEY, response.token);
          localStorage.setItem(this.ROLE_KEY, response.role);
        }
      })
    );
  }


  logout(): void {
    localStorage.removeItem(this.ROLE_KEY);
    localStorage.removeItem(this.TOKEN_KEY);
    this.router.navigate(['/login']);
  }

  isAuthenticated(): boolean {
    const token = localStorage.getItem(this.TOKEN_KEY);
    return token != null && !this.jwtHelper.isTokenExpired(token);
  }

  getToken(): string | null {
    return localStorage.getItem(this.TOKEN_KEY);
  }

  getRole(): string | null {
    return localStorage.getItem(this.ROLE_KEY);
  }
}