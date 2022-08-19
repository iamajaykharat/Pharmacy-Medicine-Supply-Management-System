import { TestBed } from '@angular/core/testing';

import { MrScheduleService } from './mr-schedule.service';

describe('MrScheduleService', () => {
  let service: MrScheduleService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(MrScheduleService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
