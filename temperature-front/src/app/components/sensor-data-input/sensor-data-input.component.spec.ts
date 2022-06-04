import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SensorDataInputComponent } from './sensor-data-input.component';

describe('SensorDataInputComponent', () => {
  let component: SensorDataInputComponent;
  let fixture: ComponentFixture<SensorDataInputComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ SensorDataInputComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(SensorDataInputComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
