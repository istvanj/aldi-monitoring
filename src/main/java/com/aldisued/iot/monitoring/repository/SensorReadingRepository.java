package com.aldisued.iot.monitoring.repository;

import com.aldisued.iot.monitoring.entity.SensorReading;
import com.aldisued.iot.monitoring.entity.SensorType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface SensorReadingRepository extends JpaRepository<SensorReading, String> {

    @Query("""
                select avg(sr.value) from SensorReading sr 
                where sr.sensor.type = :sensorType 
                    and sr.timestamp between :from and :to
            """)
    Optional<Double> findAverageReadingBySensorTypeAndTimestampBetween(@Param("sensorType") SensorType sensorType, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

    @Query("""
                select sr.value from SensorReading sr 
                where sr.sensor.type = :sensorType
                    and sr.timestamp between :from and :to
                order by sr.timestamp
            """)
    List<Double> findMeasurementValuesBySensorTypeAndTimestampBetween(@Param("sensorType") SensorType sensorType, @Param("from") LocalDateTime from, @Param("to") LocalDateTime to);

}
