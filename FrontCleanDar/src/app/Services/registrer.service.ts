import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Jwt } from '../models/Jwt';

const BASE_URL = "http://localhost:9095/api/v1/auth/register"; // URL correcte

@Injectable({
  providedIn: 'root'
})
export class RegistrerService {

  constructor(private http: HttpClient) { }

  // Correction de l'URL en supprimant la concaténation incorrecte de 'signup'
  registrer(signRequest: any): Observable<any> {
    return this.http.post(BASE_URL, signRequest);
  }
  login(loginRequest:any): Observable<Jwt>{
    return this.http.post<Jwt>(URL+'authenticate', loginRequest)
  }
  sayHello(): Observable<any> {
    const headers = this.createAuthorizationHeader();
    return this.http.get(URL + 'demo', { headers });
  }
  
  private createAuthorizationHeader(): HttpHeaders | undefined {
    const jwtToken = localStorage.getItem('jwt');
    if (jwtToken) {
      console.log("JWT token found in local storage", jwtToken);
      return new HttpHeaders().set("Authorization", "Bearer " + jwtToken);
    } else {
      console.log("JWT token not found in local storage");
      return undefined;
    }
  }
  

}
