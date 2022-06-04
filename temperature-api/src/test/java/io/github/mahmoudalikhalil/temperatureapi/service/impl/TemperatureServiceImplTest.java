package io.github.mahmoudalikhalil.temperatureapi.service.impl;

import io.github.mahmoudalikhalil.temperatureapi.dto.SensorDTO;
import io.github.mahmoudalikhalil.temperatureapi.dto.TemperatureDTO;
import io.github.mahmoudalikhalil.temperatureapi.entity.Temperature;
import io.github.mahmoudalikhalil.temperatureapi.repository.TemperatureRepository;
import io.github.mahmoudalikhalil.temperatureapi.util.TemperatureUtil;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class TemperatureServiceImplTest {

    static Clock clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
    @Mock
    TemperatureUtil util;
    @Mock
    TemperatureRepository repository;
    @InjectMocks
    TemperatureServiceImpl temperatureService;

    @Test
    void testReportingTemperature() {
        String data = "0x482226402D";
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setData(data);
        Temperature temperature = new Temperature(LocalDateTime.now(clock), 1210197588L, (short) 42);

        when(util.parseData(data)).thenReturn(List.of(temperature));

        assertThatCode(() -> temperatureService.reportTemperatures(sensorDTO))
                .doesNotThrowAnyException();

        verify(repository).saveAll(anyList());
    }

    @Test
    void testReportingTemperatureWithNull() {
        assertThatThrownBy(() -> temperatureService.reportTemperatures(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void testRetrievingRecentTemperatures() {
        Temperature temperature = new Temperature(LocalDateTime.now(clock), 1210197588L, (short) 42);

        when(repository.getRecentTemperatures()).thenReturn(List.of(temperature));

        List<TemperatureDTO> recentTemperatures = temperatureService.getRecentTemperatures();

        assertThat(recentTemperatures)
                .isNotNull()
                .isNotEmpty()
                .singleElement()
                .hasFieldOrPropertyWithValue("deviceId", 1210197588L)
                .hasFieldOrPropertyWithValue("temperature", (short) 42)
                .hasFieldOrPropertyWithValue("timestamp", LocalDateTime.now(clock));

        verify(repository).getRecentTemperatures();
    }

    @Test
    void testRetrievingDeviceTemperatures() {
        Long deviceId = 1210197588L;
        Temperature firstTemp = new Temperature(LocalDateTime.now(clock), deviceId, (short) 42);
        Temperature secondTemp = new Temperature(LocalDateTime.now(clock), deviceId, (short) 15);

        when(repository.findDeviceTemperatures(deviceId)).thenReturn(List.of(firstTemp, secondTemp));

        List<TemperatureDTO> deviceTemperatures = temperatureService.getDeviceTemperatures(deviceId);

        assertThat(deviceTemperatures)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .extracting(TemperatureDTO::getDeviceId)
                .containsOnly(deviceId);

        verify(repository).findDeviceTemperatures(deviceId);
    }

    @Test
    void testRetrievingDeviceTemperaturesWithNull() {
        assertThatThrownBy(() -> temperatureService.getDeviceTemperatures(null))
                .isExactlyInstanceOf(IllegalArgumentException.class);
    }
}