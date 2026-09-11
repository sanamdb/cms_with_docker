import { ComponentFixture, TestBed } from '@angular/core/testing';
import { NO_ERRORS_SCHEMA } from '@angular/core';
import { HttpClientTestingModule } from '@angular/common/http/testing';
import { RouterTestingModule } from '@angular/router/testing';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';

import { AdminProfileComponent } from './admin-profile.component';

describe('AdminProfileComponent', () => {
  let component: AdminProfileComponent;
  let fixture: ComponentFixture<AdminProfileComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ AdminProfileComponent ], imports: [HttpClientTestingModule, RouterTestingModule, FormsModule, ReactiveFormsModule], schemas: [NO_ERRORS_SCHEMA]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(AdminProfileComponent);
    component = fixture.componentInstance;
    component.loggedInProfile = { firstName: '', lastName: '' } as any;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
