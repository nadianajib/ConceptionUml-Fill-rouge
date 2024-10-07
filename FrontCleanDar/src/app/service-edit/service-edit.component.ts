import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router, ActivatedRoute } from '@angular/router';
import { ServiceCrudService } from '../Services/crudservice.service';
import { Service } from '../models/Service';

@Component({
  selector: 'app-service-edit',
  templateUrl: './service-edit.component.html',
  styleUrls: ['./service-edit.component.scss']
})
export class ServiceEditComponent implements OnInit {
  serviceForm: FormGroup;
  submitted = false;
  image: string | ArrayBuffer | null = null;
  serviceId!: number;

  // Changez la visibilité de router de private à public
  constructor(
    public router: Router, // Changement ici
    private formBuilder: FormBuilder,
    private route: ActivatedRoute,
    private serviceCrudService: ServiceCrudService
  ) {
    this.serviceForm = this.formBuilder.group({
      nom: ['', Validators.required],
      description: ['', Validators.required],
      prix: [0, [Validators.required, Validators.min(0)]],
      typeService: ['', Validators.required],
      image: ['', Validators.required]
    });
  }

  ngOnInit(): void {
    this.serviceId = +this.route.snapshot.paramMap.get('id')!;
    this.loadServiceData();
  }

  loadServiceData(): void {
    this.serviceCrudService.getServiceById(this.serviceId).subscribe(service => {
      this.serviceForm.patchValue(service);
      this.image = service.image;
    });
  }

  onSubmit(): void {
    this.submitted = true;

    if (this.serviceForm.invalid) {
      return;
    }

    const updateService: Service = {
      nom: this.serviceForm.value.nom,
      description: this.serviceForm.value.description,
      prix: this.serviceForm.value.prix,
      typeService: this.serviceForm.value.typeService,
      image: this.image as string
    };

    this.serviceCrudService.updateService(this.serviceId, updateService).subscribe({
      next: () => {
        this.router.navigate(['/service-list']);
      },
      error: (err) => {
        console.error('Erreur lors de la mise à jour du service:', err);
      }
    });
  }

  onFileSelected(event: Event): void {
    const file = (event.target as HTMLInputElement).files?.[0];
    if (file) {
      const reader = new FileReader();
      reader.onload = () => {
        this.image = reader.result;
      };
      reader.readAsDataURL(file);
    }
  }

  get f() {
    return this.serviceForm.controls;
  }
  // Assurez-vous que cette méthode est présente
  updateService(serviceId: number): void {
    this.router.navigate(['/service-edit', serviceId]); // Redirige vers le composant de mise à jour
  }
}
