import { TestBed } from '@angular/core/testing';
import { RouterTestingModule } from '@angular/router/testing';

import { StudentLoginInterceptor } from './student-login.interceptor';

describe('StudentLoginInterceptor', () => {
  beforeEach(() => TestBed.configureTestingModule({
    imports: [RouterTestingModule],
    providers: [
      StudentLoginInterceptor
      ]
  }));

  it('should be created', () => {
    const interceptor: StudentLoginInterceptor = TestBed.inject(StudentLoginInterceptor);
    expect(interceptor).toBeTruthy();
  });
});
