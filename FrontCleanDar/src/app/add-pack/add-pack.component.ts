import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router'; // Importez Router pour la navigation
import { Pack } from '../models/Pack'; // Chemin d'importation du modèle
import { PackService } from '../Services/pack.service';
import { UploadImage } from '../Services/UploadImage'; // Assurez-vous que ce service existe

@Component({
  selector: 'app-add-pack',
  templateUrl: './add-pack.component.html',
  styleUrls: ['./add-pack.component.scss'] // Assurez-vous d'utiliser SCSS
})
export class AddPackComponent {
  packForm: FormGroup;
  submitted = false;
  image: string | null = null; // Pour stocker l'URL de l'image

  constructor(
    private formBuilder: FormBuilder,
    private packService: PackService,
    private uploadService: UploadImage,
    private router: Router // Ajoutez Router ici
  ) {
    this.packForm = this.formBuilder.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      prixTotal: [0, [Validators.required, Validators.min(0)]],
      reduction: [0, [Validators.required, Validators.min(0)]],
      image: ['', Validators.required] // Le champ image reste, mais on l'utilisera différemment
    });
  }

  // Récupérer les contrôles du formulaire
  get f() {
    return this.packForm.controls;
  }

  // Méthode pour soumettre le formulaire
  async onSubmit() {
    this.submitted = true;

    // Arrêter ici si le formulaire n'est pas valide
    if (this.packForm.invalid) {
      return;
    }

    // Upload de l'image vers Cloudinary
    try {
      const imageUrl = await this.uploadService.uploadImageToCloudinary(this.packForm.value.image);
      const newPack: Pack = {
        ...this.packForm.value,
        image: imageUrl, // Utiliser l'URL de l'image uploadée
        id: 0 // On peut laisser l'ID à 0 si l'API le génère
      };

      // Appeler le service pour créer le pack
      this.packService.createPack(newPack).subscribe(
        (response) => {
          console.log('Pack créé avec succès!', response);
          // Rediriger vers la liste des packs
          this.router.navigate(['/pack-list']); // Remplacez '/pack-list' par votre route réelle
        },
        (error) => {
          console.error('Erreur lors de la création du pack', error);
        }
      );
    } catch (error) {
      console.error('Erreur lors de l\'upload de l\'image', error);
    }
  }

  onFileSelected(event: any): void {
    const file = event.target.files[0];
    if (file && file.type.match(/image\/*/) != null) {
      this.packForm.patchValue({
        image: file // Mettre à jour le champ image avec le fichier sélectionné
      });
      this.packForm.get('image')?.markAsTouched(); // Marquer le champ comme touché pour les validations
      const previewUrl = URL.createObjectURL(file);
      this.image = previewUrl; // Mettre à jour l'aperçu de l'image
    } else {
      alert('Veuillez sélectionner une image valide');
    }
  }
}
