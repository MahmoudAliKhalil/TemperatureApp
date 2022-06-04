package io.github.mahmoudalikhalil.temperatureapi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class TemperatureDTO {
    private Long deviceId;
    private Short temperature;
    private LocalDateTime timestamp;
}
