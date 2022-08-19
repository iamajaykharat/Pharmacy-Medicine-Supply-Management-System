import { Pipe, PipeTransform } from '@angular/core';
import { DemandDetails } from '../Model/DemandDetails';

@Pipe({
  name: 'demandFilter'
})
export class DemandFilterPipe implements PipeTransform {

  transform(demandDetails : DemandDetails[], value:string): any {
    
    if(!demandDetails || !value){
      return demandDetails;
    }
    else{
      return demandDetails.filter(res=>{
        return res.medicineName.toLowerCase().includes(value.toLowerCase())
      })
    }
  }

}
