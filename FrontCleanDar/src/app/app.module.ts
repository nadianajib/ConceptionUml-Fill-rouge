import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { MatCardModule } from '@angular/material/card';
import { MatFormFieldModule } from '@angular/material/form-field';
import { MatInputModule } from '@angular/material/input';
import { MatButtonModule } from '@angular/material/button';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { AddReservationComponent } from './add-reservation/add-reservation.component';
import { FormGroup, FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HTTP_INTERCEPTORS, HttpClientModule } from '@angular/common/http';
import { RegistrercomponentComponent } from './resgistercomponent/resgistercomponent.component';
import { LoginComponent } from './login/login.component';
import { JWT_OPTIONS, JwtHelperService } from '@auth0/angular-jwt';
import { RegistrerService } from './Services/registrer.service';
import { AuthInterceptorInterceptor } from './interceptor/auth-interceptor.interceptor';
import { ListReservationComponent } from './list-reservation/list-reservation.component';
import { NavbarComponent } from './navbar/navbar.component';
import { DashbordComponent } from './dashbord/dashbord.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { UpdateReservationComponent } from './update-reservation/update-reservation.component';
import { AddPackComponent } from './add-pack/add-pack.component';

@NgModule({
  declarations: [
    AppComponent,
    AddReservationComponent,
    RegistrercomponentComponent,
    LoginComponent,
    ListReservationComponent,
    NavbarComponent,
    UpdateReservationComponent,
    DashbordComponent,
    AddPackComponent,
    

  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    ReactiveFormsModule,
    HttpClientModule,  
    FormsModule,
    BrowserAnimationsModule,
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    BrowserAnimationsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatButtonModule, 
  ],
  providers: [
    { provide: JWT_OPTIONS, useValue: JWT_OPTIONS },
    JwtHelperService,
    [RegistrerService,
      { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptorInterceptor, multi: true }
    ],
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
