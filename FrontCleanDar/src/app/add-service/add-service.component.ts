import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceCrudService } from '../Services/crudservice.service';
import { UploadImage } from '../Services/UploadImage';

@Component({
  selector: 'app-add-service',
  templateUrl: './add-service.component.html',
  styleUrls: ['./add-service.component.scss']
})
export class AddServiceComponent {
  serviceForm: FormGroup;
  submitted = false;
  image: string | null = null;

  constructor(
    private formBuilder: FormBuilder,
    private serviceCrudService: ServiceCrudService,
    private router: Router,
    private uploadService: UploadImage
  ) {
    this.serviceForm = this.formBuilder.group({
      nom: ['', Validators.required],
      description: ['', Validators.required],
      prix: ['', [Validators.required, Validators.min(1)]],
      image: ['', Validators.required],
      typeService: ['', Validators.required]
    });
  }

  // Getter pour accéder facilement aux champs du formulaire
  get f() { return this.serviceForm.controls; }

  async onSubmit(): Promise<void> {
    this.submitted = true;

    // Stop si le formulaire est invalide
    if (this.serviceForm.invalid) {
      return;
    }

    const serviceFormValue = this.serviceForm.value;

    // Upload l'image vers Cloudinary et récupérer l'URL
    serviceFormValue.image = await this.uploadService.uploadImageToCloudinary(serviceFormValue.image);

    console.log(this.serviceForm.value);

    // Appel du service pour créer un nouveau service
    this.serviceCrudService.addService(serviceFormValue).subscribe({
      next: (response) => {
        console.log('Service ajouté avec succès', response);
        this.router.navigate(['/service-list']);
      },
      error: (error) => {
        console.error('Erreur lors de l\'ajout du service', error);
      }
    });
  }

  onFileSelected(event: any, controlName: string): void {
    const file = event.target.files[0];
    if (file && file.type.match(/image\/*/) != null) {
      const previewUrl = URL.createObjectURL(file);
      this.serviceForm.patchValue({
        [controlName]: file
      });
      this.serviceForm.get(controlName)?.markAsTouched();
      this.image = previewUrl;
    } else {
      alert('Veuillez sélectionner une image valide');
    }
  }
}
