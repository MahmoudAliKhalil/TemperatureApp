package io.github.mahmoudalikhalil.temperatureapi.util;

import io.github.mahmoudalikhalil.temperatureapi.entity.Temperature;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.time.Clock;
import java.time.LocalDateTime;
import java.util.List;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Component
public class TemperatureUtil {
    private static final Pattern TEMPERATURE_DATA_PATTERN = Pattern.compile("(\\p{XDigit}{10})", Pattern.CASE_INSENSITIVE);
    private final Clock defaultClock;

    public TemperatureUtil(Clock defaultClock) {
        this.defaultClock = defaultClock;
    }

    public List<Temperature> parseData(String data) {
        Assert.notNull(data, "Data cannot be null");

        return TEMPERATURE_DATA_PATTERN.matcher(data)
                .results()
                .map(MatchResult::group)
                .map(this::createTemperature)
                .collect(Collectors.toUnmodifiableList());
    }

    private Temperature createTemperature(String deviceData) {
        return new Temperature(LocalDateTime.now(defaultClock),
                parseDeviceId(deviceData),
                parseTemperatureValue(deviceData));
    }

    private Short parseTemperatureValue(String data) {
        return Short.parseShort(data.substring(8), 16);
    }

    private Long parseDeviceId(String data) {
        return Long.parseLong(data.substring(0, 8), 16);
    }
}
