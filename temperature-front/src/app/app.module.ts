import { NgModule } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { HttpClientModule } from '@angular/common/http';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SensorDataInputComponent } from './components/sensor-data-input/sensor-data-input.component';
import { RecentTemperatureTableComponent } from './components/recent-temperature-table/recent-temperature-table.component';
import { DeviceTemperatureTableComponent } from './components/device-temperature-table/device-temperature-table.component';

@NgModule({
  declarations: [
    AppComponent,
    SensorDataInputComponent,
    RecentTemperatureTableComponent,
    DeviceTemperatureTableComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    HttpClientModule,
    FormsModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
