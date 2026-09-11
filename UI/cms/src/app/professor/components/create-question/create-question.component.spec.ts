import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { MatDialogModule } from '@angular/material/dialog';

import { CreateQuestionComponent } from './create-question.component';

describe('CreateQuestionComponent', () => {
  let component: CreateQuestionComponent;
  let fixture: ComponentFixture<CreateQuestionComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CreateQuestionComponent ],
      imports: [HttpClientTestingModule, RouterTestingModule, FormsModule, ReactiveFormsModule, MatDialogModule],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .overrideComponent(CreateQuestionComponent, { set: { template: '' } })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CreateQuestionComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
