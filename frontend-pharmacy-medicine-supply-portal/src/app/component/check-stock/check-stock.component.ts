import { Component, OnInit } from '@angular/core';
import { MedicineStock } from 'src/app/Model/MedicineStock';
import { CheckStockService } from 'src/app/service/check-stock/check-stock.service';

@Component({
  selector: 'app-check-stock',
  templateUrl: './check-stock.component.html',
  styleUrls: ['./check-stock.component.css'],
})
export class CheckStockComponent implements OnInit {
  p: number = 1;
  medicineDetails!: MedicineStock[];
  searchContent !: string;
  constructor(private checkStockService: CheckStockService) {}

  ngOnInit(): void {
    this.checkStockService.getMedicineStock().subscribe(
      (result) => {
        this.medicineDetails = result.body as MedicineStock[];
      },
      (err) => {}
    );
  }
}
