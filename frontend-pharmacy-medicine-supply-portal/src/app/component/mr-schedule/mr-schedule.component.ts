import { THIS_EXPR } from '@angular/compiler/src/output/output_ast';
import { Component, OnInit, ViewChild } from '@angular/core';
import { NgForm } from '@angular/forms';
import { MRSchedule } from 'src/app/Model/MrSchedule';
import { AuthService } from 'src/app/service/auth-service/auth.service';
import { MrScheduleService } from 'src/app/service/mr-schedule/mr-schedule.service';

@Component({
  selector: 'app-mr-schedule',
  templateUrl: './mr-schedule.component.html',
  styleUrls: ['./mr-schedule.component.css']
})
export class MrScheduleComponent implements OnInit {

  mrSchedules !: MRSchedule[];

  currentDate !: Date;

  constructor(private mrScheduleService: MrScheduleService,
    public authService :AuthService
   ) { }

  ngOnInit(): void {
    this.currentDate = new Date();
  }

 schedule(form: NgForm){
   
    let date = form.value.scheduleDate;

    this.mrScheduleService.getMrSchedules(date).subscribe(
      result=>{
        console.log(result.body);
        
        this.mrSchedules = result.body as MRSchedule[];
       }
      )
     setTimeout(() => {
       document.getElementById('target')?.scrollIntoView({behavior:'smooth'})
     },500)
  }

}
