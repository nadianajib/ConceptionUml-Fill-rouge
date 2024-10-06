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

  // Méthode pour calculer le prix final après réduction
  calculerPrixFinal(prixTotal: number, reduction: number): number {
    const montantReduction = (prixTotal * reduction) / 100;
    const prixFinal = prixTotal - montantReduction;
    return prixFinal > 0 ? prixFinal : 0;  // S'assurer que le prix final est positif
  }

  // Création du pack avec le prix final
  createPack(pack: any): Observable<any> {
    const prixFinal = this.calculerPrixFinal(pack.prixTotal, pack.reduction);
    const packAvecPrixFinal = {
      ...pack,
      prixTotal: prixFinal  // Mettre à jour le prix total avec la réduction appliquée
    };

    return this.http.post<any>(this.apiUrl, packAvecPrixFinal);
  }
  
  getAllPacks(): Observable<Pack[]> {
    return this.http.get<Pack[]>(`${this.apiUrl}/all`); // Ajoutez "/all" pour appeler l'endpoint
  }
  // Supprimer une réservation
  deletePack(id: number): Observable<any> {
    return this.http.delete(`${this.apiUrl}/delete/${id}`);
  }
  
  

  
}
