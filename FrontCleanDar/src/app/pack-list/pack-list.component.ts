import { Component, OnInit } from '@angular/core';
import { Pack } from '../models/Pack';
import { PackService } from '../Services/pack.service';
import { RegistrerService } from '../Services/registrer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-pack-list',
  templateUrl: './pack-list.component.html',
  styleUrls: ['./pack-list.component.scss']
})
export class PackListComponent implements OnInit {
  packs: Pack[] = [];
  role?: string | null;

  constructor(private packService: PackService,
              private router: Router,
              private registrerService: RegistrerService) {}
              

  ngOnInit(): void {
    this.loadPacks();
    this.getUserRole();
  }

  getUserRole() {
    this.role = this.registrerService.getRole(); 
    console.log('User Role:', this.role);
  }

  loadPacks() {
    this.packService.getAllPacks().subscribe(
      (data: Pack[]) => {
        console.log('Données récupérées:', data); 
        this.packs = data;
      },
      error => {
        console.error('Erreur lors du chargement des packs:', error);
      }
    );
  }
   // Méthode pour rediriger vers le formulaire d'ajout de réservation
   ajouterReservation(id: number) {
    this.router.navigate(['/add-reservation', id]); 
  }

  deletePack(id: number) {
    console.log('ID à supprimer :', id); // Ajoute un log pour le débogage
    if (confirm('Voulez-vous vraiment supprimer ce pack ?')) {
      this.packService.deletePack(id).subscribe({
        next: () => {
          console.log('Pack supprimé avec succès');
          this.loadPacks(); // Recharge les packs après suppression
        },
        error: (error) => {
          console.error('Erreur lors de la suppression du pack', error);
        }
      });
    }
  }
  
  Ajouter(){
    this.router.navigate(["/add-pack"]);
  }
}
