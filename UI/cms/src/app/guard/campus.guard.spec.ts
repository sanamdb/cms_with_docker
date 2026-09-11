import { TestBed } from '@angular/core/testing';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { CampusGuard } from './campus.guard';

describe('CampusGuard', () => {
  let guard: CampusGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({ imports: [HttpClientTestingModule, RouterTestingModule] });
    guard = TestBed.inject(CampusGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
