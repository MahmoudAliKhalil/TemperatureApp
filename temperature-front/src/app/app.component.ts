import { Component } from '@angular/core';
import { interval, timer } from 'rxjs';
import { takeWhile, timeInterval } from 'rxjs/operators';
import { Temperature } from './model/Temperature.model';
import { TemperatureService } from './services/temperature.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  title = 'Temperature Reporting App';
  isDeviceTableVisible: boolean = false;
  deviceTemperatures: Temperature[];

  constructor(private temperatureService: TemperatureService) { }

  getDeviceTemperatures(deviceId: number): void {
    this.isDeviceTableVisible = true;
    timer(0, 10000)
      .pipe(takeWhile(() => this.isDeviceTableVisible))
      .subscribe(() => {
        this.temperatureService.getDeviceTemperatures(deviceId)
          .subscribe(response => {
            if (response != null) {
              this.deviceTemperatures = response;
            }
          })
      });
  }
}
