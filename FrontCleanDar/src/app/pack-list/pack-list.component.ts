import { Component, OnInit } from '@angular/core';
import { Pack } from '../models/Pack';
import { PackService } from '../Services/pack.service';

@Component({
  selector: 'app-pack-list',
  templateUrl: './pack-list.component.html',
  styleUrls: ['./pack-list.component.scss']
})
export class PackListComponent implements OnInit {
  packs: Pack[] = [];
  imageString:string ="assets/pack/"

  constructor(private packService: PackService) {}

  ngOnInit(): void {
    this.loadPacks();
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
