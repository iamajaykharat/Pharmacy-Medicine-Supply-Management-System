import { HttpClient, HttpHeaders, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { ApiResponse } from 'src/app/Model/ApiResponse';
import { SignInRequest } from 'src/app/Model/SignInRequest';
import { SignUpRequest } from 'src/app/Model/SignUpRequest';
import {SIGN_UP_URL,SIGN_IN_URL,VALIDATE_URL} from 'src/app/common/Urls';
import { map, catchError } from 'rxjs/operators';
import { BehaviorSubject } from 'rxjs';
import { LoginResponse } from 'src/app/component/signin/signin.component';

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  apiResponse: ApiResponse = {
    status:false,
    message:''
  };

  currentUser : string = localStorage.getItem("username")! || sessionStorage.getItem("username")!;

  isLoggedIn = false;
   
  constructor(private http: HttpClient) { }

  
  login(userDetails : SignInRequest){
    return this.http.post<LoginResponse>(SIGN_IN_URL,
                          userDetails,
                          {observe:'response'});
  }

  signup(userDetails : SignUpRequest){
    return this.http.post(SIGN_UP_URL,
                          userDetails,
                          {observe:'response'});
                              
  }

   async tokenValidator(token: string) :Promise<boolean>{
    
    const promise =   this.http.get(VALIDATE_URL,
                            {headers:new HttpHeaders({
                              'Authorization' : token 
                            }),
                            observe:"response",
                            responseType:'json'
                          }).toPromise();
                          
    
     await promise.then(
     res =>{
      let data = JSON.stringify(res.body);
          let response = JSON.parse(data);
          this.apiResponse.status = true;
        }
      )
      .catch(
        err =>{
          this.apiResponse.status = false;
        }
      )
      return this.apiResponse.status;
    }

}
