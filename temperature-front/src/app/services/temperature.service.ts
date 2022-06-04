import { HttpClient, HttpResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { environment } from 'src/environments/environment';
import { SensorData } from '../model/SensorData.model';
import { Temperature } from '../model/Temperature.model';

@Injectable({
  providedIn: 'root'
})
export class TemperatureService {
  private url = environment.apiUrl + "temperatures";

  constructor(private httpClient: HttpClient) { }

  getRecentTemperatures(): Observable<Temperature[]> {
    return this.httpClient.get<Temperature[]>(this.url);
  }

  getDeviceTemperatures(deviceId: number): Observable<Temperature[]> {
    return this.httpClient.get<Temperature[]>(this.url + `/devices/${deviceId}`);
  }

  reportTemperatures(sensorsData: SensorData): Observable<HttpResponse<any>> {
    return this.httpClient.post<any>(this.url, sensorsData, {observe: "response"});
  }
}
