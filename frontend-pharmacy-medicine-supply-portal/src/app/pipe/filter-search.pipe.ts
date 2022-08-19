import { Pipe, PipeTransform } from '@angular/core';
import { PharmacyMedicineSupply } from '../Model/PharmacyMedicineSupply';

@Pipe({
  name: 'filterSearch'
})
export class FilterSearchPipe implements PipeTransform {

  transform(orders : PharmacyMedicineSupply[],value: string): PharmacyMedicineSupply[] {
    
    if(!orders || !value){
      return orders;
    }

   return orders.filter(res=>{
      return res.medicineName.toLocaleLowerCase().includes(value.toLocaleLowerCase()) ||
       res.pharmacyName.toLocaleLowerCase().includes(value.toLocaleLowerCase()) 
     })
  }

}
