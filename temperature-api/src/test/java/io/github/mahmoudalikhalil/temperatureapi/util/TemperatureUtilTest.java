package io.github.mahmoudalikhalil.temperatureapi.util;

import io.github.mahmoudalikhalil.temperatureapi.entity.Temperature;
import io.github.mahmoudalikhalil.temperatureapi.entity.TemperatureId;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class TemperatureUtilTest {

    static TemperatureUtil util;
    static Clock clock;

    @BeforeAll
    static void beforeAll() {
        clock = Clock.fixed(Instant.now(), ZoneId.systemDefault());
        util = new TemperatureUtil(clock);
    }

    @Test
    void testParsingSingleSensorData() {
        String data = "0x4822264014";
        List<Temperature> temperatures = util.parseData(data);
        assertThat(temperatures).isNotNull().isNotEmpty().singleElement().isNotNull().satisfies(temp -> {
            assertThat(temp.getId()).extracting(TemperatureId::getDeviceId).isEqualTo(1210197568L);
            assertThat(temp.getId()).extracting(TemperatureId::getTimestamp).isEqualTo(LocalDateTime.now(clock));
            assertThat(temp.getTemperature()).isNotNull().isEqualTo((short) 20);
        });
    }

    @Test
    void testParsingMultipleSensorData() {
        String data = "0x00AAbb2C100000bb2211F0AAbb2C1F";
        List<Temperature> temperatures = util.parseData(data);
        assertThat(temperatures).isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .extracting("id.deviceId", "id.timestamp", "temperature")
                .containsExactly(
                        Tuple.tuple(11189036L, LocalDateTime.now(clock), (short) 16),
                        Tuple.tuple(47906L, LocalDateTime.now(clock), (short) 17),
                        Tuple.tuple(4037720876L, LocalDateTime.now(clock), (short) 31)
                );
    }

    @Test
    void testParsingSensorDataWithoutPrefix() {
        String data = "4822264014";
        List<Temperature> temperatures = util.parseData(data);
        assertThat(temperatures).isNotNull().isNotEmpty().singleElement().isNotNull().satisfies(temp -> {
            assertThat(temp.getId()).extracting(TemperatureId::getDeviceId).isEqualTo(1210197568L);
            assertThat(temp.getId()).extracting(TemperatureId::getTimestamp).isEqualTo(LocalDateTime.now(clock));
            assertThat(temp.getTemperature()).isNotNull().isEqualTo((short) 20);
        });
    }

    @Test
    void testParsingSensorDataLimits() {
        String data = "0xFFFFFFFFFF";
        List<Temperature> temperatures = util.parseData(data);
        assertThat(temperatures).isNotNull().isNotEmpty().singleElement().isNotNull().satisfies(temp -> {
            assertThat(temp.getId()).extracting(TemperatureId::getDeviceId).isEqualTo(4_294_967_295L);
            assertThat(temp.getId()).extracting(TemperatureId::getTimestamp).isEqualTo(LocalDateTime.now(clock));
            assertThat(temp.getTemperature()).isNotNull().isEqualTo((short) 255);
        });
    }

    @Test
    void testParsingSensorDataWithNull() {
        assertThatThrownBy(() -> util.parseData(null))
                .isExactlyInstanceOf(IllegalArgumentException.class)
                .hasMessage("Data cannot be null");
    }

    @Test
    void testParsingWithEmptySensorData() {
        List<Temperature> temperatures = util.parseData("");
        assertThat(temperatures)
                .isNotNull()
                .isEmpty();
    }
}