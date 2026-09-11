import { TestBed } from '@angular/core/testing';

import { BannerSlideService } from './banner-slide.service';

describe('BannerSlideService', () => {
  let service: BannerSlideService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(BannerSlideService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
