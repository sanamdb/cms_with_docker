import { TestBed } from '@angular/core/testing';

import { DobValidatorService } from './dob-validator.service';

describe('DobValidatorService', () => {
  let service: DobValidatorService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DobValidatorService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
