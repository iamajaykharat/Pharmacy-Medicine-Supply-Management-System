import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { MEDICINE_STOCK_INFORMATION_URL } from 'src/app/common/Urls';
@Injectable({
  providedIn: 'root'
})
export class CheckStockService {
  constructor(
    private http: HttpClient
  ) { }

  getMedicineStock(){
    return this.http.get(MEDICINE_STOCK_INFORMATION_URL,
                          {observe:'response',responseType:'json'}
      );
  }
}
