import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { PackService } from '../Services/pack.service';
import { UploadImage } from '../Services/UploadImage';

@Component({
  selector: 'app-add-pack',
  templateUrl: './add-pack.component.html',
  styleUrls: ['./add-pack.component.scss']
})
export class AddPackComponent {
  packForm: FormGroup;
  submitted = false;
  image : String | null = null;

  constructor(private formBuilder: FormBuilder, private packService: PackService, private router: Router, private uploadService: UploadImage) {
    // Initialisation du formulaire
    this.packForm = this.formBuilder.group({
      titre: ['', Validators.required],
      description: ['', Validators.required],
      prixTotal: ['', [Validators.required, Validators.min(1)]],
      reduction: ['', [Validators.required, Validators.min(1)]],
      image: ['', Validators.required] 

    });
  }

  // Getter pour accéder facilement aux champs du formulaire
  get f() { return this.packForm.controls; }

  async onSubmit() {
    this.submitted = true;

    // Stop si le formulaire est invalide
    if (this.packForm.invalid) {
      return;
    }      
    const packform = this.packForm.value
    
    packform.image = await this.uploadService.uploadImageToCloudinary(packform.image);

    console.log(this.packForm.value)

    // Appel du service pour créer un nouveau pack
    this.packService.createPack(this.packForm.value).subscribe({
      next: (response) => {
        console.log('Pack créé avec succès', response);
        this.router.navigate(['/pack-list']);
      },
      error: (error) => {
        console.error('Erreur lors de la création du pack', error);
      }
    });
  }    

  onFileSelected(event: any, controlName: string): void {
    const file = event.target.files[0];
    if (file && file.type.match(/image\/*/) != null) {
      const previewUrl = URL.createObjectURL(file);
      this.packForm.patchValue({
        [controlName]: file
      });
      this.packForm.get(controlName)?.markAsTouched();
        this.image = previewUrl;
    } else {
      alert('Veuillez sélectionner une image valide');
    }
  }
}