import { Component, OnInit } from '@angular/core';
import { Service } from '../models/Service';
import { ServiceCrudService } from '../Services/crudservice.service';

@Component({
  selector: 'app-service-list',
  templateUrl: './service-list.component.html',
  styleUrls: ['./service-list.component.scss']
})
export class ServiceListComponent implements OnInit {
  services: Service[] = []; // Tableau pour stocker les services
  errorMessage: string = ''; // Pour stocker les messages d'erreur

  constructor(private crudService: ServiceCrudService) {} // Changez ici le nom

  ngOnInit(): void {
    this.loadServices(); // Charger les services au démarrage
  }

  // Méthode pour charger tous les services
  loadServices(): void {
    this.crudService.getAllServices().subscribe(
      (data: Service[]) => {
        this.services = data; // Assigner les données reçues au tableau
      },
      error => {
        this.errorMessage = 'Erreur lors de la récupération des services'; // Gérer les erreurs
        console.error(error); // Log des erreurs dans la console
      }
    );
  }
}
