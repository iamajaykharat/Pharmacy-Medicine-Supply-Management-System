import { Injectable } from '@angular/core';
import { ToastrService } from 'ngx-toastr';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {

  constructor(private toster: ToastrService) { }

  showSuccessBottom(message:string, title:string){
    this.toster.success(message,title,{
      positionClass:"toast-bottom-right"
    });
  }
  
  showSuccessTop(message:string, title:string){
    this.toster.success(message,title,{
      positionClass:"toast-top-right"
    });
  }
}
