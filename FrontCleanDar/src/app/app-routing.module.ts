import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { RegisterComponent } from './resgistercomponent/resgistercomponent.component';

const routes: Routes = [
  { path: 'add-reservation', component: AddReservationComponent },
  { path: 'register', component: RegisterComponent }, // Correction ici, sans le "/"
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
