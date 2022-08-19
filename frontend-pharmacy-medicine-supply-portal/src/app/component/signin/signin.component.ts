import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { ActivatedRoute, Router } from '@angular/router';
import { ApiResponse } from 'src/app/Model/ApiResponse';
import { SignInRequest } from 'src/app/Model/SignInRequest';
import { AuthService } from 'src/app/service/auth-service/auth.service';

export class LoginResponse {
  success: boolean = false;
  token: string = "";
}

@Component({
  selector: 'app-signin',
  templateUrl: './signin.component.html',
  styleUrls: ['./signin.component.css'],
})
export class SigninComponent implements OnInit {
  user: SignInRequest = {
    username: '',
    password: '',
  };

  errorResponse!: string;

  constructor(
    private route: Router,
    private authService: AuthService,
    private auth: ActivatedRoute
  ) {}

  ngOnInit(): void {
    if (localStorage.getItem("Authorization") != null || sessionStorage.getItem("Authorization") != null) {
      this.route.navigateByUrl('/home');
    }
  }

  signIn(form: NgForm) {
    this.user.username = form.value['username'];
    this.user.password = form.value['password'];
    this.authService.login(this.user).subscribe(
      (res) => {
        console.log(res.body?.token)
        form.value['rememberMe']
          ? this.remember(true, res.body?.token!)
          : this.remember(false, res.body?.token!);
        this.authService.isLoggedIn = true;
        this.route.navigate(['/home'])
        this.authService.currentUser = this.user.username;
        sessionStorage.setItem('username',this.user.username);
        localStorage.setItem('username',this.user.username);
      },
      (err) => {
        
        this.errorResponse = err.error;
      }
    );
  }

  remember(rememberMe: boolean, token: string) {
    if (token != null) {
      rememberMe
        ? localStorage.setItem('Authorization', token)
        : sessionStorage.setItem('Authorization', token);
    }
  }
}
