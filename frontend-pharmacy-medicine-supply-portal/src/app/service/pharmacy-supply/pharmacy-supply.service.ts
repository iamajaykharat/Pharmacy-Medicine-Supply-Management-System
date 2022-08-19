import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import {
  PHARMACY_SUPPLY_URL,
  PHARMACY_SUPPLY_VIEW_DEMAND_URL,
  PHARMACY_SUPPLY_DETAILS_URL,
} from 'src/app/common/Urls';

@Injectable({
  providedIn: 'root',
})
export class PharmacySupplyService {
  constructor(private http: HttpClient) {}

  getPharmacySupply(medicineDemand: []) {
    return this.http.post(PHARMACY_SUPPLY_URL, medicineDemand, {
      observe: 'response',
    });
  }

  getDemandDetails() {
    return this.http.get(PHARMACY_SUPPLY_VIEW_DEMAND_URL, {
      observe: 'response',
    });
  }

  pharmacySupplyDetails() {
    return this.http.get(PHARMACY_SUPPLY_DETAILS_URL, { observe: 'response' });
  }
}
