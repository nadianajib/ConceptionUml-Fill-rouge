import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Pack } from '../models/Pack';
import { PackService } from '../Services/pack.service';

@Component({
  selector: 'app-edit-pack',
  templateUrl: './edit-pack.component.html',
  styleUrls: ['./edit-pack.component.scss']
})
export class EditPackComponent implements OnInit {
  editPackForm: FormGroup;
  submitted = false;
  packId!: number;

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private packService: PackService
  ) {
    this.editPackForm = this.formBuilder.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      prixTotal: [0, [Validators.required, Validators.min(0)]],
      reduction: [0, Validators.min(0)] // La réduction peut être 0 ou plus
    });
  }

  ngOnInit(): void {
    this.packId = +this.route.snapshot.paramMap.get('id')!; // Récupérer l'ID du pack à partir des paramètres de la route
    this.loadPackData();
  }

  loadPackData(): void {
    this.packService.getPackById(this.packId).subscribe({
      next: (pack: Pack) => {
        this.editPackForm.patchValue(pack); // Charger les données du pack dans le formulaire
      },
      error: (err) => {
        console.error('Erreur lors du chargement des données du pack:', err);
      }
    });
  }

  onSubmit(): void {
    this.submitted = true;

    // Arrêter le processus si le formulaire est invalide
    if (this.editPackForm.invalid) {
      return;
    }

    const editPack: Pack = {
        titre: this.editPackForm.value.titre,
        description: this.editPackForm.value.description,
        prixTotal: this.editPackForm.value.prixTotal,
        reduction: this.editPackForm.value.reduction,
        id: this.packId,
        image: ''
    };

    this.packService.editPack(this.packId, editPack).subscribe({
      next: () => {
        console.log(editPack);
        console.log(this.packId);
        this.router.navigate(['/pack-list']); // Rediriger vers la liste des packs après la mise à jour
      },
      error: (err) => {
        console.error('Erreur lors de la mise à jour du pack:', err);
      }
    });
  }

  get f() {
    return this.editPackForm.controls; // Accéder facilement aux contrôles du formulaire
  }
}
