import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MrScheduleComponent } from './mr-schedule.component';

describe('MrScheduleComponent', () => {
  let component: MrScheduleComponent;
  let fixture: ComponentFixture<MrScheduleComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ MrScheduleComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(MrScheduleComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
