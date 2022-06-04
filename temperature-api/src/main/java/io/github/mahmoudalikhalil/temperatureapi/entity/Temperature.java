package io.github.mahmoudalikhalil.temperatureapi.entity;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class Temperature {
    @EmbeddedId
    private TemperatureId id;
    private Short temperature;

    public Temperature(LocalDateTime timestamp, Long deviceId, Short temperature) {
        this.id = new TemperatureId(timestamp, deviceId);
        this.temperature = temperature;
    }
}
