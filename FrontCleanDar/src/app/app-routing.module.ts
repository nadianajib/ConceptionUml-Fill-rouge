import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { RegistrercomponentComponent } from './resgistercomponent/resgistercomponent.component';
import { LoginComponent } from './login/login.component';
import { ListReservationComponent } from './list-reservation/list-reservation.component';
import { DashbordComponent } from './dashbord/dashbord.component';
import { NavbarComponent } from './navbar/navbar.component';
import { AddPackComponent } from './add-pack/add-pack.component';
import { PackListComponent } from './pack-list/pack-list.component';
import { UpdateReservationComponent } from './update-reservation/update-reservation.component';
import { HomeComponent } from './home/home.component';
import { AddServiceComponent } from './add-service/add-service.component';
import { ServiceListComponent } from './service-list/service-list.component';
import { ServiceEditComponent } from './service-edit/service-edit.component';

const routes: Routes = [
  { path: 'dashboard', component: DashbordComponent, children: [
    { path: '', component: NavbarComponent },
  ]},
  { path: 'home', component: HomeComponent},
  { path: '', redirectTo: 'home', pathMatch: 'full' },
  { path: 'add-reservation/:packId', component: AddReservationComponent },
  { path: 'register', component: RegistrercomponentComponent },
  { path: 'login', component: LoginComponent },
  { path: 'list-reservation', component: ListReservationComponent },
  { path: 'add-pack', component: AddPackComponent },
  { path: 'update-reservation/:id', component: UpdateReservationComponent },
  {path:  'pack-list', component: PackListComponent},
  {path:  'AddService', component: AddServiceComponent},
  {path:  'service-list', component: ServiceListComponent},
  { path: 'service-edit/:id', component: ServiceEditComponent },





];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
