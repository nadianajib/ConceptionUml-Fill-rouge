import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { Service } from '../models/Service';
import { ServiceCrudService } from '../Services/crudservice.service';

@Component({
  selector: 'app-pack-service',
  templateUrl: './pack-service.component.html',
  styleUrls: ['./pack-service.component.scss']
})
export class PackServiceComponent implements OnInit {
  services: Service[] = [];
  errorMessage: string = '';
  packId: number = 0;
  isLoading: boolean = false;

  constructor(
    private crudService: ServiceCrudService,
    private route: ActivatedRoute,
    private router: Router
  ) {}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.packId = +params['packId']; // Le '+' convertit la chaîne en nombre
      if (this.packId) {
        this.loadServicesByPackId(this.packId);
      } else {
        this.errorMessage = 'ID du pack non valide';
      }
    });
  }

  loadServicesByPackId(packId: number): void {
    console.log(`Chargement des services pour le pack ${packId}`);
    this.isLoading = true;
    this.errorMessage = '';
    console.log(packId);
    this.crudService.getAllServicesByPackId(packId).subscribe(
      (data: Service[]) => {
        console.log('Services reçus:', data);
        this.services = data;
        this.isLoading = false;
      },
      error => {
        this.isLoading = false;
        this.errorMessage = 'Erreur lors de la récupération des services pour ce pack';
        console.error('Erreur:', error);
        if (error.error instanceof ErrorEvent) {
          // Erreur côté client
          console.error('Erreur client:', error.error.message);
        } else {
          // Erreur côté serveur
          console.error(`Erreur serveur: ${error.status}, message: ${error.error}`);
          if (error.status === 401) {
            this.errorMessage = 'Erreur .';
          } else if (error.status === 404) {
            this.errorMessage = 'Aucun service trouvé pour ce pack.';
          }
        }
      }
    );
  }
  onAddService(): void {
    this.router.navigate(['/AddService']); // Remplacez par la route de votre formulaire d'ajout de service
}

  updateService(serviceId: number): void {
    this.router.navigate(['/service-edit', serviceId]); // Redirige vers le composant de mise à jour
  }

  retryLoading(): void {
    if (this.packId) {
      this.loadServicesByPackId(this.packId);
    }
  }
  deleteServices(id: number | undefined): void {
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

}