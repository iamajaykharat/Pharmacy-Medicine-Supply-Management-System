import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { REPRESENTATIVE_SCHEDULE_URL } from "src/app/common/Urls";
@Injectable({
  providedIn: 'root'
})
export class MrScheduleService {

  constructor(
    private http: HttpClient
  ) { }
  
  getMrSchedules(date: string){
    return this.http.get(REPRESENTATIVE_SCHEDULE_URL + `/${date}`,{
      observe:"response",
      responseType: 'json'
    }
    )
  }

}
