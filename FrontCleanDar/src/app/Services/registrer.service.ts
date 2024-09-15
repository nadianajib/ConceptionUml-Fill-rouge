import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';

const BASE_URL = "http://localhost:9095/api/v1/auth/register"; // URL correcte

@Injectable({
  providedIn: 'root'
})
export class RegistrerService {

  constructor(private http: HttpClient) { }

  registrer(signRequest: any): Observable<any> {
    return this.http.post(BASE_URL, signRequest);
  }
}
