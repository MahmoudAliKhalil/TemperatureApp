import { ComponentFixture, TestBed } from '@angular/core/testing';

import { DeviceTemperatureTableComponent } from './device-temperature-table.component';

describe('DeviceTemperatureTableComponent', () => {
  let component: DeviceTemperatureTableComponent;
  let fixture: ComponentFixture<DeviceTemperatureTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ DeviceTemperatureTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(DeviceTemperatureTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
