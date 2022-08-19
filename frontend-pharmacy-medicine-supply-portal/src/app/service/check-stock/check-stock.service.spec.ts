import { TestBed } from '@angular/core/testing';

import { CheckStockService } from './check-stock.service';

describe('CheckStockService', () => {
  let service: CheckStockService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(CheckStockService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
