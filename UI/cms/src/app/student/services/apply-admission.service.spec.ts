import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { ApplyAdmissionService } from './apply-admission.service';

describe('ApplyAdmissionService', () => {
  let service: ApplyAdmissionService;

  beforeEach(() => {
    TestBed.configureTestingModule({ imports: [HttpClientTestingModule] });
    service = TestBed.inject(ApplyAdmissionService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
