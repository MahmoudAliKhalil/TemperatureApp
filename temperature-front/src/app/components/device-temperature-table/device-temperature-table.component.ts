import { Component, Input, OnInit } from '@angular/core';
import { Temperature } from 'src/app/model/Temperature.model';

@Component({
  selector: 'app-device-temperature-table',
  templateUrl: './device-temperature-table.component.html',
  styleUrls: ['./device-temperature-table.component.css']
})
export class DeviceTemperatureTableComponent implements OnInit {

  @Input() temperatures: Temperature[] = [];

  constructor() { }

  ngOnInit(): void {
  }

}
