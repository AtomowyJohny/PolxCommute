import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MechanikDetailsComponent } from './mechanik-details.component';

describe('MechanikDetailsComponent', () => {
  let component: MechanikDetailsComponent;
  let fixture: ComponentFixture<MechanikDetailsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MechanikDetailsComponent]
    })
    .compileComponents();

    fixture = TestBed.createComponent(MechanikDetailsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
