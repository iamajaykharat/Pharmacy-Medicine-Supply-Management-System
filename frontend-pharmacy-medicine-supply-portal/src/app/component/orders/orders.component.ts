import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { PharmacyMedicineSupply } from 'src/app/Model/PharmacyMedicineSupply';
import { PharmacySupplyService } from 'src/app/service/pharmacy-supply/pharmacy-supply.service';

@Component({
  selector: 'app-orders',
  templateUrl: './orders.component.html',
  styleUrls: ['./orders.component.css']
})
export class OrdersComponent implements OnInit {
  p = 1;
  orders : PharmacyMedicineSupply[] = [];
  
  searchContent !:string;
  
  constructor(
    private pharmacySupplyService : PharmacySupplyService
  ){
  }

  ngOnInit(): void {
      this.pharmacySupplyService.pharmacySupplyDetails().subscribe(
                                  res =>{
                                    this.orders = res.body as PharmacyMedicineSupply[];
                                   
                                  },
                                  err =>{
                                    console.log(err.error);
                                    
                                  }
                                )
    
  }


 

}