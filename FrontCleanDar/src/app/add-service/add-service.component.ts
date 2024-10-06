import { Component } from '@angular/core';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ServiceCrudService } from '../Services/crudservice.service';

@Component({
  selector: 'app-add-service',
  templateUrl: './add-service.component.html',
  styleUrls: ['./add-service.component.scss']
})
export class AddServiceComponent {
  serviceForm: FormGroup;

  constructor(
    private formBuilder: FormBuilder,
    private serviceCrudService: ServiceCrudService,
    private router: Router
  ) {
    this.serviceForm = this.formBuilder.group({
      nom: ['', Validators.required],
      description: ['', Validators.required],
      prix: ['', [Validators.required, Validators.min(1)]],
      image: ['', Validators.required],
      typeService: ['', Validators.required]
    });
  }

  onSubmit(): void {
    if (this.serviceForm.valid) {
      const newService = this.serviceForm.value;
      this.serviceCrudService.addService(newService).subscribe({
        next: (service) => {
          console.log('Service ajouté avec succès', service);
          this.router.navigate(['/services']);
        },
        error: (err) => {
          console.error('Erreur lors de l\'ajout du service', err);
        }
      });
    }
  }

}
