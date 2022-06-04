package io.github.mahmoudalikhalil.temperatureapi.service.impl;

import io.github.mahmoudalikhalil.temperatureapi.dto.SensorDTO;
import io.github.mahmoudalikhalil.temperatureapi.dto.TemperatureDTO;
import io.github.mahmoudalikhalil.temperatureapi.entity.Temperature;
import io.github.mahmoudalikhalil.temperatureapi.mapper.TemperatureMapper;
import io.github.mahmoudalikhalil.temperatureapi.repository.TemperatureRepository;
import io.github.mahmoudalikhalil.temperatureapi.service.TemperatureService;
import io.github.mahmoudalikhalil.temperatureapi.util.TemperatureUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class TemperatureServiceImpl implements TemperatureService {

    private final TemperatureRepository temperatureRepository;
    private final TemperatureUtil temperatureUtil;

    public TemperatureServiceImpl(TemperatureRepository temperatureRepository, TemperatureUtil temperatureUtil) {
        this.temperatureRepository = temperatureRepository;
        this.temperatureUtil = temperatureUtil;
    }

    @Transactional
    @Override
    public void reportTemperatures(SensorDTO sensorData) {
        Assert.notNull(sensorData, "sensorData cannot be null");

        String data = sensorData.getData();
        List<Temperature> temperatures = temperatureUtil.parseData(data);
        temperatureRepository.saveAll(temperatures);
    }

    @Override
    public List<TemperatureDTO> getRecentTemperatures() {
        return temperatureRepository.getRecentTemperatures()
                .stream()
                .map(TemperatureMapper.INSTANCE::map)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public List<TemperatureDTO> getDeviceTemperatures(Long deviceId) {
        Assert.notNull(deviceId, "deviceId cannot be null");

        return temperatureRepository.findDeviceTemperatures(deviceId)
                .stream()
                .map(TemperatureMapper.INSTANCE::map)
                .collect(Collectors.toUnmodifiableList());
    }
}
