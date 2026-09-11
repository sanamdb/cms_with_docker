import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { ExamService } from './exam.service';

describe('ExamService', () => {
  let service: ExamService;

  beforeEach(() => {
    TestBed.configureTestingModule({ imports: [HttpClientTestingModule] });
    service = TestBed.inject(ExamService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
