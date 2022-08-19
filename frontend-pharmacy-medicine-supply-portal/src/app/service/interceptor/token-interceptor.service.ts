import { HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { NgxSpinnerService } from 'ngx-spinner';
import { Observable } from 'rxjs';
import { tap } from 'rxjs/operators';

@Injectable({
  providedIn: 'root'
})
export class TokenInterceptorService implements HttpInterceptor{

  constructor(
  ) { }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    if(!req.url.endsWith('/signin') && !req.url.endsWith('/signup')){
      let token = (localStorage.getItem('Authorization'))! || (sessionStorage.getItem('Authorization'))!
      let tokenizedRequest = req.clone({
        setHeaders:{
          Authorization: token
        }
      });
      return next.handle(tokenizedRequest);
    }else{
      console.log("in interceptor", req)
      return next.handle(req);
    }
  }
}
