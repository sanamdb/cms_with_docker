import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule, HttpTestingController } from '@angular/common/http/testing';

import { AboutUsService } from './about-us.service';

describe('AboutUsService', () => {
  let service: AboutUsService;
  let httpMock: HttpTestingController

  beforeEach(() => {
    TestBed.configureTestingModule({ 
      imports: [HttpClientTestingModule], 
      providers: [AboutUsService] 
    });
    service = TestBed.inject(AboutUsService);
    httpMock = TestBed.inject(HttpTestingController);
  });

  afterEach(() => {
    httpMock.verify();
  })

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('Faq Service Call', () => {

    const mockFaqs = [
      { id: 1, question: 'What is Jest?', answer: 'A testing framework.' },
      { id: 2, question: 'Is Angular fun?', answer: 'Yes!' }
    ];


    service.allFaq().subscribe(faq => {
      expect(faq.length).toBe(2);
    });

    const req = httpMock.expectOne('/college/api/v1/faq');
    expect(req.request.method).toBe('GET');

    req.flush(mockFaqs);

  })

});
