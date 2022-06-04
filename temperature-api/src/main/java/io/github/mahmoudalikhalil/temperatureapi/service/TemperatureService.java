package io.github.mahmoudalikhalil.temperatureapi.service;

import io.github.mahmoudalikhalil.temperatureapi.dto.SensorDTO;
import io.github.mahmoudalikhalil.temperatureapi.dto.TemperatureDTO;

import java.util.List;

public interface TemperatureService {
    void reportTemperatures(SensorDTO sensorData);

    List<TemperatureDTO> getRecentTemperatures();

    List<TemperatureDTO> getDeviceTemperatures(Long deviceId);
}
