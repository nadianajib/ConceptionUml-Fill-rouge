import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Service } from '../models/Service';

@Injectable({
  providedIn: 'root'
})
export class ServiceCrudService {
  private apiUrl = 'http://localhost:9095/api/services/Admin'; // Base URL de ton API

  constructor(private http: HttpClient) {}

  // Méthode pour ajouter un nouveau service
  addService(service: Service): Observable<Service> {
    return this.http.post<Service>(`${this.apiUrl}/add`, service);
  }

  // Méthode pour récupérer tous les services
  getAllServices(): Observable<Service[]> {
    return this.http.get<Service[]>(`${this.apiUrl}/all`);
  }

  // Méthode pour récupérer un service par ID
  getServiceById(id: number): Observable<Service> {
    return this.http.get<Service>(`${this.apiUrl}/${id}`);
  }

  // Méthode pour mettre à jour un service
  updateService(id: number, service: Service): Observable<Service> {
    return this.http.put<Service>(`${this.apiUrl}/${id}`, service);
  }

    // Méthode pour supprimer un service par ID
    deleteService(id: number): Observable<void> {
      return this.http.delete<void>(`${this.apiUrl}/Delete/${id}`);
    }
}
