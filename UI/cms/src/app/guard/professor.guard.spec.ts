import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { ProfessorGuard } from './professor.guard';

describe('ProfessorGuard', () => {
  let guard: ProfessorGuard;

  beforeEach(() => {
    TestBed.configureTestingModule({ imports: [RouterTestingModule] });
    guard = TestBed.inject(ProfessorGuard);
  });

  it('should be created', () => {
    expect(guard).toBeTruthy();
  });
});
