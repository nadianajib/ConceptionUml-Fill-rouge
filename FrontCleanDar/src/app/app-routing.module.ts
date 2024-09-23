import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { RegistrercomponentComponent } from './resgistercomponent/resgistercomponent.component';
import { LoginComponent } from './login/login.component';
import { ListReservationComponent } from './list-reservation/list-reservation.component';

const routes: Routes = [
  { path: 'add-reservation', component: AddReservationComponent },
  { path: 'register', component: RegistrercomponentComponent },
  { path: 'login', component: LoginComponent },
  { path: 'list-reservation', component: ListReservationComponent},





];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
