import { TestBed } from '@angular/core/testing';

import { PharmacySupplyService } from './pharmacy-supply.service';

describe('PharmacySupplyService', () => {
  let service: PharmacySupplyService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(PharmacySupplyService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
