import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { DetailSuppliesComponent } from './detail-supplies.component';

describe('DetailSuppliesComponent', () => {
  let component: DetailSuppliesComponent;
  let fixture: ComponentFixture<DetailSuppliesComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ DetailSuppliesComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(DetailSuppliesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
