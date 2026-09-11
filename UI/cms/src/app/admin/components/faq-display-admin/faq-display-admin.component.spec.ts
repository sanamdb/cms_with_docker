import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FaqDisplayAdminComponent } from './faq-display-admin.component';

describe('FaqDisplayAdminComponent', () => {
  let component: FaqDisplayAdminComponent;
  let fixture: ComponentFixture<FaqDisplayAdminComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ FaqDisplayAdminComponent ]
    })
    .compileComponents();
  });

  beforeEach(() => {
    fixture = TestBed.createComponent(FaqDisplayAdminComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
