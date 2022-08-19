import { Component, OnInit } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MedicineStock } from 'src/app/Model/MedicineStock';
import { MedicineDemand } from 'src/app/Model/MedicineDemand';
import { CheckStockService } from 'src/app/service/check-stock/check-stock.service';
import { PharmacySupplyService } from 'src/app/service/pharmacy-supply/pharmacy-supply.service';
import { Router } from '@angular/router';
import { ToastrService } from 'ngx-toastr';
import { NotificationService } from 'src/app/service/notification-service/notification.service';

@Component({
  selector: 'app-place-order',
  templateUrl: './place-order.component.html',
  styleUrls: ['./place-order.component.css'],
})
export class PlaceOrderComponent implements OnInit {
  medicineStock!: MedicineStock[];
  medicineDemand = new MedicineDemand();

  orders: any = [];
  constructor(private checkStockService: CheckStockService,
    private pharmacySupply : PharmacySupplyService, 
    private router : Router,
    private toster: NotificationService) {}

  ngOnInit(): void {
    
    this.orders.push(this.medicineDemand);
    
    this.checkStockService.getMedicineStock()
    .subscribe((result) => {
      this.medicineStock = result.body as MedicineStock[];
    });

  }

  placeOrder(){
    
    this.pharmacySupply.getPharmacySupply(this.orders).subscribe(
                                     res =>{
                                       this.toster.showSuccessBottom("Order has been placed Successfully","Order Placed")
                                       this.router.navigate(['/orders'])
                                     } 
                                     
                            )
                             
    
  }

  addOrder(medicine:MedicineStock,demandCount:number) {
    this.medicineDemand = new MedicineDemand()
    this.orders.push(this.medicineDemand);
  }

  removeOrder(i: number) {
    if(this.orders.length != 1){
      this.orders.splice(i, 1);
    }
  }
}
