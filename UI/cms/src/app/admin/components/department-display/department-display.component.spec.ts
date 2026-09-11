import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { FilterDeptPipe } from '../../pipes/filter-dept.pipe';

import { DepartmentDisplayComponent } from './department-display.component';

describe('DepartmentDisplayComponent', () => {
  let component: DepartmentDisplayComponent;
  let fixture: ComponentFixture<DepartmentDisplayComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DepartmentDisplayComponent, FilterDeptPipe ],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(DepartmentDisplayComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
