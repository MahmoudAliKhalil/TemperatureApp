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

//    @Query(value = "SELECT t1.timestamp, t1.device_id, t1.temperature FROM temperature AS t1 INNER JOIN (SELECT MAX(timestamp) AS leastTime, device_id FROM temperature GROUP BY device_id) AS t2 ON t1.timestamp = t2.leastTime AND t1.device_id = t2.device_id ORDER BY t1.timestamp DESC", nativeQuery = true)
    @Query("SELECT t1 FROM Temperature t1 where t1.id.timestamp = (SELECT MAX(t2.id.timestamp) FROM Temperature t2 where t1.id.deviceId = t2.id.deviceId) ORDER BY t1.id.timestamp")
    List<Temperature> getRecentTemperatures();
}
