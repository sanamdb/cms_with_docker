import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AddProfessorComponent } from './add-professor.component';

describe('AddProfessorComponent', () => {
  let component: AddProfessorComponent;
  let fixture: ComponentFixture<AddProfessorComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddProfessorComponent ], imports: [HttpClientTestingModule, RouterTestingModule, FormsModule, ReactiveFormsModule], schemas: [NO_ERRORS_SCHEMA]
    })
    .overrideComponent(AddProfessorComponent, { set: { template: '' } })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddProfessorComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
