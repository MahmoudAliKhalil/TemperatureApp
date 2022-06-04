import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RecentTemperatureTableComponent } from './recent-temperature-table.component';

describe('RecentTemperatureTableComponent', () => {
  let component: RecentTemperatureTableComponent;
  let fixture: ComponentFixture<RecentTemperatureTableComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RecentTemperatureTableComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RecentTemperatureTableComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
