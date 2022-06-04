package io.github.mahmoudalikhalil.temperatureapi.controller;

import io.github.mahmoudalikhalil.temperatureapi.dto.SensorDTO;
import io.github.mahmoudalikhalil.temperatureapi.dto.TemperatureDTO;
import io.github.mahmoudalikhalil.temperatureapi.service.TemperatureService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/temperatures")
@CrossOrigin("*")
public class TemperatureController {

    private final TemperatureService temperatureService;

    public TemperatureController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
    }

    @GetMapping
    public List<TemperatureDTO> getTemperatures() {
        return temperatureService.getRecentTemperatures();
    }

    @GetMapping("/devices/{deviceId}")
    public List<TemperatureDTO> getDeviceTemperatures(@PathVariable("deviceId") Long deviceId) {
        return temperatureService.getDeviceTemperatures(deviceId);
    }

    @PostMapping
    public ResponseEntity<Void> addTemperature(@RequestBody @Valid SensorDTO temperature) {
        temperatureService.reportTemperatures(temperature);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

}
