package io.github.mahmoudalikhalil.temperatureapi.mapper;

import io.github.mahmoudalikhalil.temperatureapi.dto.TemperatureDTO;
import io.github.mahmoudalikhalil.temperatureapi.entity.Temperature;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TemperatureMapper {
    TemperatureMapper INSTANCE = Mappers.getMapper(TemperatureMapper.class);

    @Mapping(source = "id.deviceId", target = "deviceId")
    @Mapping(source = "id.timestamp", target = "timestamp")
    @Mapping(source = "temperature", target = "temperature")
    TemperatureDTO map(Temperature temperature);
}
