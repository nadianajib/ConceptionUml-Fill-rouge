import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Pack } from '../models/Pack'; // Assurez-vous que vous avez un modèle Pack défini

@Injectable({
  providedIn: 'root'
})
export class PackService {

  private apiUrl = 'http://localhost:9095/api/v1/packs/Admin';  // Remplacez par l'URL de votre API

  constructor(private http: HttpClient) { }

  // Méthode pour créer un nouveau pack
  createPack(pack: Pack): Observable<Pack> {
    return this.http.post<Pack>(`${this.apiUrl}/add`, pack);
  }
}
