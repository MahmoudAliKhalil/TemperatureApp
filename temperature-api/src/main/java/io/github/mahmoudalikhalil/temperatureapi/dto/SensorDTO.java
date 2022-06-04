package io.github.mahmoudalikhalil.temperatureapi.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
public class SensorDTO {
    @NotBlank(message = "Data cannot be null or empty")
    @Pattern(regexp = "^0x(?:\\p{XDigit}{10})+?", flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid data")
    private String data;
}
