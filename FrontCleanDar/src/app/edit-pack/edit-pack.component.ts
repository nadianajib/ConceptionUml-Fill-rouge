import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { Pack } from '../models/Pack';
import { PackService } from '../Services/pack.service';
import { UploadImage } from '../Services/UploadImage';

@Component({
  selector: 'app-edit-pack',
  templateUrl: './edit-pack.component.html',
  styleUrls: ['./edit-pack.component.scss']
})
export class EditPackComponent implements OnInit {
  editPackForm: FormGroup;
  submitted = false;
  packId!: number;
  image: string | null = null; // URL de prévisualisation de l'image
  selectedFile: File | null = null; // Fichier d'image sélectionné
  isLoading = false; // Indicateur de chargement

  constructor(
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private router: Router,
    private packService: PackService,
    private uploadService: UploadImage
  ) {
    this.editPackForm = this.formBuilder.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      prixTotal: [0, [Validators.required, Validators.min(0)]],
      reduction: [0, Validators.min(0)],
      image: [''] // Champ pour l'URL de l'image
    });
  }

  ngOnInit(): void {
    this.packId = +this.route.snapshot.paramMap.get('id')!;
    this.loadPackData();
  }

  loadPackData(): void {
    this.packService.getPackById(this.packId).subscribe({
      next: (pack: Pack) => {
        this.editPackForm.patchValue(pack);
        this.image = pack.image; // Charger l'image existante
      },
      error: (err) => console.error('Erreur lors du chargement du pack:', err)
    });
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    const maxSize = 5 * 1024 * 1024; // 5MB maximum

    if (file && file.size > maxSize) {
      alert('Le fichier est trop volumineux. Veuillez sélectionner une image de moins de 5MB.');
      return;
    }

    if (file && file.type.match(/image\/*/) != null) {
      const reader = new FileReader();
      reader.onload = () => {
        this.image = reader.result as string;
        this.selectedFile = file;
      };
      reader.readAsDataURL(file);
    } else {
      alert('Veuillez sélectionner une image valide.');
    }
  }

  async onSubmit(): Promise<void> {
    this.submitted = true;
    if (this.editPackForm.invalid) return;

    this.isLoading = true;

    try {
      let imageUrl = this.image;
      if (this.selectedFile) {
        imageUrl = await this.uploadService.uploadImageToCloudinary(this.selectedFile);
      }

      const editPack: Pack = {
        ...this.editPackForm.value,
        id: this.packId,
        image: imageUrl
      };

      this.packService.editPack(this.packId, editPack).subscribe({
        next: () => {
          alert('Pack mis à jour avec succès !');
          this.router.navigate(['/pack-list']);
        },
        error: (err) => {
          console.error('Erreur lors de la mise à jour:', err);
          alert('Erreur lors de la mise à jour du pack. Veuillez réessayer.');
        }
      });
    } catch (error) {
      console.error('Erreur lors du téléchargement de l\'image:', error);
      alert('Erreur lors du téléchargement de l\'image. Veuillez réessayer.');
    } finally {
      this.isLoading = false;
    }
  }

  get f() {
    return this.editPackForm.controls;
  }
}
