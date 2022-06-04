package io.github.mahmoudalikhalil.temperatureapi.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.time.LocalDateTime;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TemperatureId implements Serializable {
    private LocalDateTime timestamp;
    @Column(name = "device_id")
    private Long deviceId;
}
