import { Pipe, PipeTransform } from '@angular/core';
import { MedicineStock } from '../Model/MedicineStock';

@Pipe({
  name: 'stockfilter'
})
export class StockfilterPipe implements PipeTransform {

  transform(medicineDetails: MedicineStock[], value: string): any {
    
    if(!medicineDetails || !value){
      return medicineDetails;
    }
    else{
      return medicineDetails.filter(res=>{
        return res.medicineName.toLowerCase().includes(value.toLowerCase()) ||
        res.chemicalComposition.toLowerCase().includes(value.toLowerCase()) ||
        res.pharmacyName.toLowerCase().includes(value.toLowerCase()) ||
        res.targetAilment.toLowerCase().includes(value.toLowerCase())
      })
    }
  }

}
