import { ComponentFixture, TestBed } from '@angular/core/testing';

import { StudentInfoAboutUsComponent } from './student-info-about-us.component';

describe('StudentInfoAboutUsComponent', () => {
  let component: StudentInfoAboutUsComponent;
  let fixture: ComponentFixture<StudentInfoAboutUsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ StudentInfoAboutUsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(StudentInfoAboutUsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
