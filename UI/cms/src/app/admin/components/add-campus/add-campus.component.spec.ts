import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AddCampusComponent } from './add-campus.component';

describe('AddCampusComponent', () => {
  let component: AddCampusComponent;
  let fixture: ComponentFixture<AddCampusComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AddCampusComponent ], imports: [HttpClientTestingModule, RouterTestingModule, FormsModule, ReactiveFormsModule], schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AddCampusComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
