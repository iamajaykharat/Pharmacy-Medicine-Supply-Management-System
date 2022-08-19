import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NgxSpinnerService } from 'ngx-spinner';
import { Observable } from 'rxjs';
import { AuthService } from './service/auth-service/auth.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
})
export class AppComponent implements OnInit{
  title = 'PharmacyMedicineSupplyPortal';
 
  constructor(private router: Router,
    private route: ActivatedRoute,
    public authService: AuthService) {}

  ngOnInit(): void {
      this.authService.currentUser = sessionStorage.getItem('username')! || localStorage.getItem("username")!;
    if(localStorage.getItem('Authorization')!=null || sessionStorage.getItem("Authorization")!=null){
      this.authService.isLoggedIn=true;
   }
    
  }


  logout() {
   localStorage.clear();
   sessionStorage.clear();
   this.authService.isLoggedIn = false;
   this.router.navigateByUrl("/signin")
  }
}


