import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ServiceCrudService } from '../Services/crudservice.service';
import { UploadImage } from '../Services/UploadImage';
import { Pack } from '../models/Pack';
import { PackService } from '../Services/pack.service';

@Component({
  selector: 'app-add-service',
  templateUrl: './add-service.component.html',
  styleUrls: ['./add-service.component.scss']
})
export class AddServiceComponent implements OnInit {
  serviceForm: FormGroup;
  submitted = false;
  image: string | null = null;
  pack: Pack[] = [];

  packId: number = 0; // Variable pour stocker l'ID du pack

  constructor(
    private formBuilder: FormBuilder,
    private serviceCrudService: ServiceCrudService,
    private packservice : PackService,
    private router: Router,
    private route: ActivatedRoute,
    private uploadService: UploadImage
  ) {
    // Initialisation du formulaire
    this.serviceForm = this.formBuilder.group({
      nom: ['', Validators.required],
      description: ['', Validators.required],
      prix: ['', [Validators.required, Validators.min(1)]],
      image: ['', Validators.required],
      pack_id: ['', Validators.required],
    });

    this.packservice.getAllPacks().subscribe((pack : Pack[]) =>{
      this.pack = pack; // Stockage des packs
    })
  }

  ngOnInit(): void {
    // Récupérer l'ID du pack depuis les paramètres de l'URL
    this.route.params.subscribe(params => {
        if (params['packId']) {
            this.packId = +params['packId']; // Convertir la chaîne en nombre
            this.serviceForm.patchValue({ packId: this.packId }); // Ajouter l'ID du pack au formulaire
        }
    });
}


  get f() { return this.serviceForm.controls; }

  async onSubmit(): Promise<void> {
    this.submitted = true;

    // Stop si le formulaire est invalide
    if (this.serviceForm.invalid) {
        return;
    }

    const serviceFormValue = this.serviceForm.value;

    console.log('Valeurs du formulaire:', serviceFormValue); // Ajout de ce log

    try {
        // Upload de l'image vers Cloudinary et récupération de l'URL
        serviceFormValue.image = await this.uploadService.uploadImageToCloudinary(serviceFormValue.image);
        
        // Appel du service pour créer un nouveau service
        this.serviceCrudService.addService(serviceFormValue)
        .subscribe({
            next: (response) => {
                console.log('Service ajouté avec succès', response);
                this.router.navigate(['/service-list']);
            },
            error: (error) => {
                console.error('Erreur lors de l\'ajout du service', error);
                alert('Erreur lors de l\'ajout du service. Veuillez réessayer.');
            }
        });
    } catch (error) {
        console.error('Erreur lors du téléchargement de l\'image', error);
        alert('Erreur lors du téléchargement de l\'image. Veuillez réessayer.');
    }
}


  onFileSelected(event: any, controlName: string): void {
    const file = event.target.files[0];
    if (file && file.type.match(/image\/*/) != null) {
      const reader = new FileReader();
      reader.onload = () => {
        this.image = reader.result as string; // Prévisualisation de l'image
        this.serviceForm.patchValue({
          [controlName]: file
        });
        this.serviceForm.get(controlName)?.markAsTouched();
      };
      reader.readAsDataURL(file); // Pour afficher la prévisualisation
    } else {
      alert('Veuillez sélectionner une image valide.');
    }
  }
}
