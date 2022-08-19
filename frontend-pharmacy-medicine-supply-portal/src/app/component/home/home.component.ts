import { Component, EventEmitter, Input, OnInit, Output } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { AuthService } from 'src/app/service/auth-service/auth.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  constructor(private authService : AuthService,
    private route : ActivatedRoute) { }
  isLoggedIn = this.authService.isLoggedIn;
  
  ngOnInit(): void {
   
    this.isLoggedIn = this.authService.isLoggedIn;
  }


 
}
