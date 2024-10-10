import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { HttpClientModule, HTTP_INTERCEPTORS } from '@angular/common/http';
import { RegistrercomponentComponent } from './resgistercomponent/resgistercomponent.component';
import { LoginComponent } from './login/login.component';
import { JWT_OPTIONS, JwtHelperService } from '@auth0/angular-jwt';
import { RegistrerService } from './Services/registrer.service';
import { AuthInterceptorInterceptor } from './interceptor/auth-interceptor.interceptor';
import { ListReservationComponent } from './list-reservation/list-reservation.component';
import { NavbarComponent } from './navbar/navbar.component';
import { DashbordComponent } from './dashbord/dashbord.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { AddPackComponent } from './add-pack/add-pack.component';
import { PackListComponent } from './pack-list/pack-list.component';
import { HomeComponent } from './home/home.component';
import { AddServiceComponent } from './add-service/add-service.component';
import { ServiceListComponent } from './service-list/service-list.component';
import { ServiceEditComponent } from './service-edit/service-edit.component';
import { PackServiceComponent } from './pack-service/pack-service.component';
import { AboutComponent } from './about/about.component';
import { EditPackComponent } from './edit-pack/edit-pack.component';
import { UpdateReservationComponent } from './update-reservation/update-reservation.component';

@NgModule({
  declarations: [
    AppComponent,
    AddReservationComponent,
    RegistrercomponentComponent,
    LoginComponent,
    ListReservationComponent,
    NavbarComponent,
    DashbordComponent,
    AddPackComponent,
    PackListComponent,
    HomeComponent,
    AddServiceComponent,
    ServiceListComponent,
    ServiceEditComponent,
    PackServiceComponent,
    AboutComponent,
    EditPackComponent,
    UpdateReservationComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
  
    HttpClientModule,  
    FormsModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule, 
    MatIconModule,
  ],
  providers: [
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    RegistrerService,
    { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorInterceptor, multi: true },
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
