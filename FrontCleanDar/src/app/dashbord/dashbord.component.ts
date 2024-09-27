import { Component } from '@angular/core';
import { RegistrerService } from '../Services/registrer.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-dashbord',
  templateUrl: './dashbord.component.html',
  styleUrls: ['./dashbord.component.scss']
})
export class DashbordComponent {
  userRole!: string | null;


  constructor(private authService: RegistrerService, private router: Router) {}

  ngOnInit() {
    this.userRole = localStorage.getItem("role");
    console.log("======================================");
    console.log(this.userRole)
  }
}
