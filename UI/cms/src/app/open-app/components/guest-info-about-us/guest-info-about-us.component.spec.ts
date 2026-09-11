import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuestInfoAboutUsComponent } from './guest-info-about-us.component';

describe('GuestInfoAboutUsComponent', () => {
  let component: GuestInfoAboutUsComponent;
  let fixture: ComponentFixture<GuestInfoAboutUsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ GuestInfoAboutUsComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(GuestInfoAboutUsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
