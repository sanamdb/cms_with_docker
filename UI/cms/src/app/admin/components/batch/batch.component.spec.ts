import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { MatDialogModule } from '@angular/material/dialog';
import { ReactiveFormsModule, FormsModule } from '@angular/forms'; // ADD THIS
import { BatchComponent } from './batch.component';

describe('BatchComponent', () => {
  let component: BatchComponent;
  let fixture: ComponentFixture<BatchComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ BatchComponent ],
      imports: [ReactiveFormsModule, FormsModule, HttpClientTestingModule, RouterTestingModule, MatDialogModule],
      schemas: [NO_ERRORS_SCHEMA]
    })
    .overrideComponent(BatchComponent, { set: { template: '' } })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(BatchComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
