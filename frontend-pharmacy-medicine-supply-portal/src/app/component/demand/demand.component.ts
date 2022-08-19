import { HttpClient } from '@angular/common/http';
import { Component, OnInit } from '@angular/core';
import { DemandDetails } from 'src/app/Model/DemandDetails';
import { PharmacyMedicineSupply } from 'src/app/Model/PharmacyMedicineSupply';
import { PharmacySupplyService } from 'src/app/service/pharmacy-supply/pharmacy-supply.service';

@Component({
  selector: 'app-demand',
  templateUrl: './demand.component.html',
  styleUrls: ['./demand.component.css']
})
export class DemandComponent implements OnInit {
  p =1;
  demandDetails !: DemandDetails[];

  searchContent !:string;
  
  constructor(
    private pharmacySupplyService : PharmacySupplyService
  ) { }

  ngOnInit(): void {
    this.pharmacySupplyService.getDemandDetails()
                              .subscribe(
                                result => {
                                  console.log(result.body)
                                  let temp = JSON.stringify(result.body)
                                  this.demandDetails = JSON.parse(temp);
                                },
                                err =>{
                                    console.log(err.error);
                                    
                                }
                                
                              )
  }



}
