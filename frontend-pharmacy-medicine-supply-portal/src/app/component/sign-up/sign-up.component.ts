import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { Router } from '@angular/router';
import { SignUpRequest } from 'src/app/Model/SignUpRequest';
import { AuthService } from 'src/app/service/auth-service/auth.service';
import { NotificationService } from 'src/app/service/notification-service/notification.service';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css'],
})
export class SignUpComponent implements OnInit {
  user: SignUpRequest = {
    name: '',
    username: '',
    password: '',
  };

  errorResponse !: string;
  constructor(
    private authService: AuthService,
    private route: Router,
    private notification : NotificationService
    ) {}

  ngOnInit(): void {}

  signUp(form: NgForm) {
    this.user = form.value 
    
      this.authService.signup(this.user)
                .subscribe(
                  result => {
                    this.notification.showSuccessTop("Sign In to access your account","Account Created");
                    this.route.navigateByUrl("/signin");
                  }
                  ,
                  err =>{
                    this.errorResponse = err.error
                    
                  }
                )
    
  }
}
