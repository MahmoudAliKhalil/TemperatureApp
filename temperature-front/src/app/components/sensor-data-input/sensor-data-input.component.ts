import { Component, OnInit } from '@angular/core';
import { SensorData } from 'src/app/model/SensorData.model';
import { TemperatureService } from 'src/app/services/temperature.service';

@Component({
  selector: 'app-sensor-data-input',
  templateUrl: './sensor-data-input.component.html',
  styleUrls: ['./sensor-data-input.component.css']
})
export class SensorDataInputComponent implements OnInit {
  isValid: boolean;
  sensorData: SensorData = { data: "" };

  constructor(private temperatureService: TemperatureService) { }

  ngOnInit(): void {
  }

  validate(): void {
    const regex = /^0x(?:[0-9A-F]{10})+?$/i;
    this.isValid = regex.test(this.sensorData?.data);
    if (this.isValid) {
      this.sendData();
    }
  }

  private sendData(): void {
    this.temperatureService.reportTemperatures(this.sensorData)
      .subscribe(response => {
        if (response.status == 201) {
          document.getElementById("sensorData").classList.remove('is-valid');
        }
        this.sensorData.data = "";
      });
  }

}
