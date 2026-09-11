import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';

import { FaqService } from './faq.service';

describe('FaqService', () => {
  let service: FaqService;

  beforeEach(() => {
    TestBed.configureTestingModule({ imports: [HttpClientTestingModule] });
    service = TestBed.inject(FaqService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
