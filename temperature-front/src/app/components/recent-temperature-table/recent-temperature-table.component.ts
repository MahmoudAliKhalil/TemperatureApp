import { Component, EventEmitter, OnDestroy, OnInit, Output } from '@angular/core';
import { interval, timer } from 'rxjs';
import { takeWhile } from 'rxjs/operators';
import { Temperature } from 'src/app/model/Temperature.model';
import { TemperatureService } from 'src/app/services/temperature.service';

@Component({
  selector: 'app-recent-temperature-table',
  templateUrl: './recent-temperature-table.component.html',
  styleUrls: ['./recent-temperature-table.component.css']
})
export class RecentTemperatureTableComponent implements OnInit, OnDestroy {
  temperatures: Temperature[] = [];
  @Output() deviceSelected = new EventEmitter<number>();
  private isAlive: boolean;

  constructor(private temperatureService: TemperatureService) { }

  ngOnInit(): void {
    this.isAlive = true;
    timer(0, 10000)
      .pipe(takeWhile(() => this.isAlive))
      .subscribe(() => {
        this.temperatureService.getRecentTemperatures()
          .subscribe(response => {
            if (response != null) {
              this.temperatures = response;
            }
          });
      });
  }

  onClick(deviceId: number): void {
    this.deviceSelected.emit(deviceId);
  }

  ngOnDestroy(): void {
    this.isAlive = false;
  }

}
