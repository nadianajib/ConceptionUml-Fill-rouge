import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Reservation } from '../models/Reservation';

@Injectable({
  providedIn: 'root'
})
export class ReservationService {
  private apiUrl = 'http://localhost:9095/api/v1/auth/User/reservation';

  constructor(private http: HttpClient) { }

  addReservation(reservation: Reservation): Observable<Reservation> {
    const headers = this.createAuthorizationHeader();
    return this.http.post<Reservation>(`${this.apiUrl}/add`, reservation, { headers });
  }

  private createAuthorizationHeader(): HttpHeaders {
    const jwtToken = localStorage.getItem('jwt');
    if (jwtToken) {
      console.log("JWT token trouvé dans le localStorage", jwtToken);
      return new HttpHeaders().set("Authorization", "Bearer " + jwtToken);
    } else {
      console.log("JWT token non trouvé dans le localStorage");
      return new HttpHeaders();
    }
  }
}
