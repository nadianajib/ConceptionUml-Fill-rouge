import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
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
    private route: ActivatedRoute
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

  retryLoading(): void {
    if (this.packId) {
      this.loadServicesByPackId(this.packId);
    }
  }
  deleteServices(id: number): void {
    console.log(`Tentative de suppression du service avec ID: ${id}`);
    if (confirm('Voulez-vous vraiment supprimer cette réservation ?')) {
        this.crudService.deleteServices(id).subscribe({
            next: () => {
                console.log('Réservation supprimée avec succès');
                this.loadServicesByPackId(this.packId); // Recharge les réservations après la suppression
            },
            error: (error) => {
                console.error('Erreur lors de la suppression de la réservation', error);
            }
        });
    }
}

}