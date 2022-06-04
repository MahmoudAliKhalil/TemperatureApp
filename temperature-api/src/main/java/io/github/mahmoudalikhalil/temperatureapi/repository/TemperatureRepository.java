package io.github.mahmoudalikhalil.temperatureapi.repository;

import io.github.mahmoudalikhalil.temperatureapi.entity.Temperature;
import io.github.mahmoudalikhalil.temperatureapi.entity.TemperatureId;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TemperatureRepository extends PagingAndSortingRepository<Temperature, TemperatureId> {
    @Query("select temp from Temperature temp where temp.id.deviceId = :deviceId order by temp.id.timestamp desc")
    List<Temperature> findDeviceTemperatures(@Param("deviceId") Long deviceId);

    @Query("SELECT t1 FROM Temperature t1 where t1.id.timestamp = (SELECT MAX(t2.id.timestamp) FROM Temperature t2 where t1.id.deviceId = t2.id.deviceId) ORDER BY t1.id.timestamp")
    List<Temperature> getRecentTemperatures();
}
