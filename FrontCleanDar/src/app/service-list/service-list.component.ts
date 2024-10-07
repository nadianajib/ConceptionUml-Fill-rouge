import { Component, OnInit } from '@angular/core';
import { Service } from '../models/Service';
import { ServiceCrudService } from '../Services/crudservice.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-service-list',
  templateUrl: './service-list.component.html',
  styleUrls: ['./service-list.component.scss']
})
export class ServiceListComponent implements OnInit {
  services: Service[] = [];
  errorMessage: string = '';

  constructor(private crudService: ServiceCrudService, private router: Router) {}

  ngOnInit(): void {
    this.loadServices();
  }

  loadServices(): void {
    this.crudService.getAllServices().subscribe(
      (data: Service[]) => {
        this.services = data;
      },
      error => {
        this.errorMessage = 'Erreur lors de la récupération des services';
        console.error(error);
      }
    );
  }

  deleteService(id: number | undefined): void {
    if (id === undefined) {
      this.errorMessage = 'Impossible de supprimer un service sans identifiant';
      return;
    }

    if (confirm('Êtes-vous sûr de vouloir supprimer ce service ?')) {
      this.crudService.deleteService(id).subscribe(
        () => {
          this.services = this.services.filter(service => service.id !== id);
        },
        error => {
          this.errorMessage = 'Erreur lors de la suppression du service';
          console.error(error);
        }
      );
    }
  }

  updateService(serviceId: number): void {
    this.router.navigate(['/service-edit', serviceId]); // Redirige vers le composant de mise à jour
  }
}
