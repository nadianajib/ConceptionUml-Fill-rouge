import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { RegistrercomponentComponent } from './resgistercomponent/resgistercomponent.component';
import { LoginComponent } from './login/login.component';
import { ListReservationComponent } from './list-reservation/list-reservation.component';
import { TestComponent } from './test/test.component';
import { DashbordComponent } from './dashbord/dashbord.component';
import { NavbarComponent } from './navbar/navbar.component';

const routes: Routes = [

  { path: 'dashboard', component: DashbordComponent, children: [
    { path: '', component: NavbarComponent },
  

  ]},
  // {path: 'dashboard',component: DashbordComponent },
  { path : '', redirectTo: 'dashboard', pathMatch : 'full'},
  // { path: 'add-reservation', component: AddReservationComponent },
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
