import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { catchError, Observable, of } from 'rxjs';
import { Pack } from '../models/Pack'; // Assurez-vous que vous avez un modèle Pack défini

@Injectable({
  providedIn: 'root'
})
export class PackService {

  private apiUrl = 'http://localhost:9095/api/v1/packs';  // Remplacez par l'URL de votre API

  constructor(private http: HttpClient) { }

  // Méthode pour créer un pack
  createPack(pack: Pack): Observable<Pack> {
    return this.http.post<Pack>(`${this.apiUrl}/add`, pack);
  }
  
  getAllPacks(): Observable<Pack[]> {
    return this.http.get<Pack[]>(`${this.apiUrl}/all`); // Ajoutez "/all" pour appeler l'endpoint
  }
  // Supprimer une réservation
  deletePack(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${id}`);
  }
  
  editPack(id: number, pack: Pack): Observable<Pack | null> {
    return this.http.put<Pack>(`${this.apiUrl}/${id}`, pack)
      .pipe(
        catchError(error => {
          console.error('Erreur lors de la mise à jour du pack:', error);
          return of(null); // Retourne null en cas d'erreur
        })
      );
  }

  getPackById(id: number): Observable<Pack> {
    return this.http.get<Pack>(`${this.apiUrl}/${id}`);
}
}
