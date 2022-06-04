package io.github.mahmoudalikhalil.temperatureapi.mapper;

import io.github.mahmoudalikhalil.temperatureapi.dto.TemperatureDTO;
import io.github.mahmoudalikhalil.temperatureapi.entity.Temperature;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.assertj.core.api.Assertions.assertThat;

class TemperatureMapperTest {

    private static TemperatureMapper mapper = TemperatureMapper.INSTANCE;

    @Test
    void testMappingTemperatureToDTO() {
        LocalDateTime fixedTime = LocalDateTime.now(Clock.fixed(Instant.now(), ZoneId.systemDefault()));

        Temperature temperature = new Temperature(fixedTime, 1234567890L, (short) 40);

        TemperatureDTO temperatureDTO = mapper.map(temperature);

        assertThat(temperatureDTO).isNotNull().satisfies(temp -> {
            assertThat(temp.getDeviceId()).isNotNull().isEqualTo(temperature.getId().getDeviceId());
            assertThat(temp.getTemperature()).isNotNull().isEqualTo(temperature.getTemperature());
            assertThat(temp.getTimestamp()).isNotNull().isEqualTo(fixedTime);
        });
    }

    @Test
    void testMappingWithNull() {
        TemperatureDTO temperatureDTO = mapper.map(null);
        assertThat(temperatureDTO).isNull();
    }


    @Test
    void testMappingWithEmptyTemperature() {
        TemperatureDTO temperatureDTO = mapper.map(new Temperature());
        assertThat(temperatureDTO)
                .isNotNull()
                .extracting(TemperatureDTO::getDeviceId, TemperatureDTO::getTemperature, TemperatureDTO::getTimestamp)
                .containsNull();
    }
}