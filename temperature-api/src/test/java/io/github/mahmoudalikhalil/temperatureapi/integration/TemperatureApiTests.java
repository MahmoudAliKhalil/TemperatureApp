package io.github.mahmoudalikhalil.temperatureapi.integration;

import io.github.mahmoudalikhalil.temperatureapi.dto.SensorDTO;
import io.github.mahmoudalikhalil.temperatureapi.dto.TemperatureDTO;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
class TemperatureApiTests {

    @Autowired
    TestRestTemplate testRestTemplate;

    @Test
    void testRecentTemperatures() {
        ResponseEntity<List<TemperatureDTO>> response = testRestTemplate.exchange("/temperatures", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        });

        List<TemperatureDTO> temperatures = response.getBody();

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);


        assertThat(temperatures)
                .isNotNull()
                .isNotEmpty()
                .hasSize(3)
                .extracting(TemperatureDTO::getDeviceId, TemperatureDTO::getTemperature)
                .containsOnly(
                        Tuple.tuple(47906L, (short) 51),
                        Tuple.tuple(11189036L, (short) 50),
                        Tuple.tuple(4037720876L, (short) 255)
                );
    }

    @Test
    void testDeviceTemperatures() {
        Long deviceId = 47906L;
        ResponseEntity<List<TemperatureDTO>> response = testRestTemplate.exchange("/temperatures/devices/{deviceId}", HttpMethod.GET, null, new ParameterizedTypeReference<>() {
        }, deviceId);

        List<TemperatureDTO> temperatures = response.getBody();

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.OK);


        assertThat(temperatures)
                .isNotNull()
                .isNotEmpty()
                .hasSize(2)
                .extracting(TemperatureDTO::getDeviceId)
                .containsOnly(deviceId);
    }

    @Test
    void testReportTemperatures() {
        String data = "0xF0AABB2CFF";
        SensorDTO sensorData = new SensorDTO();
        sensorData.setData(data);

        ResponseEntity<List<TemperatureDTO>> response = testRestTemplate.exchange("/temperatures", HttpMethod.POST, new HttpEntity<>(sensorData), new ParameterizedTypeReference<>() {
        });

        assertThat(response.getStatusCode())
                .isEqualTo(HttpStatus.CREATED);
    }

}
