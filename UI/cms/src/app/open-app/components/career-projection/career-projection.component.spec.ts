import { Component } from '@angular/core';
import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CareerProjectionComponent } from './career-projection.component';

@Component({ template: '<app-career-projection><span #message></span></app-career-projection>' })
class CareerProjectionHostComponent {}

describe('CareerProjectionComponent', () => {
  let component: CareerProjectionComponent;
  let fixture: ComponentFixture<CareerProjectionHostComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ CareerProjectionComponent, CareerProjectionHostComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(CareerProjectionHostComponent);
    fixture.detectChanges();
    component = fixture.debugElement.children[0].componentInstance;
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
