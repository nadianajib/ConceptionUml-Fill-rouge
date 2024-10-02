import { Component, OnInit } from '@angular/core';
import { Pack } from '../models/Pack';
import { PackService } from '../Services/pack.service';
import { RegistrerService } from '../Services/registrer.service';

@Component({
  selector: 'app-pack-list',
  templateUrl: './pack-list.component.html',
  styleUrls: ['./pack-list.component.scss']
})
export class PackListComponent implements OnInit {
  packs: Pack[] = [];
  imageString:string ="assets/pack/"
  role?: string | null;

  constructor(private packService: PackService,
    private registrerService : RegistrerService
  ) {}

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
        console.log(data); // Check data structure
        this.packs = data;
        console.log(data)

      },
      error => {
        console.error('Erreur lors du chargement des packs:', error);
      }
    );
  }
  
}  
