import { Injectable } from '@angular/core';
import {
  ActivatedRoute,
  ActivatedRouteSnapshot,
  CanActivate,
  Router,
  RouterStateSnapshot,
  UrlTree,
} from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from '../service/auth-service/auth.service';

@Injectable({
  providedIn: 'root',
})
export class AuthGuard implements CanActivate {
  constructor(
    private authService: AuthService,
    private route: Router,
    private router: ActivatedRoute
  ) {}

  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot
  ):
    | Observable<boolean | UrlTree>
    | Promise<boolean | UrlTree>
    | boolean
    | UrlTree {
    let isValid: boolean = true;
    if (
      (localStorage.length != 0 && localStorage.getItem('Authorization') != null) ||
      (sessionStorage.length != 0 && sessionStorage.getItem('Authorization') != null)
    ) {
      let token =
        (localStorage.length != 0 &&
         localStorage.getItem('Authorization') != null)
          ? localStorage.getItem('Authorization')
          : sessionStorage.getItem('Authorization');
      this.authService
        .tokenValidator(token!)
        .then((res) => {
          if (!res) {
            localStorage.clear();
            sessionStorage.clear();
            this.authService.isLoggedIn = false;
            this.route.navigate(['/error']);
            isValid = false;
          }
        });
    } else {
      this.authService.isLoggedIn = false;
      this.route.navigate(['/signin']);
    }

    return isValid;
  }
}
