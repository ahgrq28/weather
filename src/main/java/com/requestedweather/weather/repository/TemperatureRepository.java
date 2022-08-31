package com.requestedweather.weather.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.requestedweather.weather.entity.Temperature;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TemperatureRepository extends JpaRepository<Temperature, UUID> {

    @Query("select t from Temperature t where (t.city = :city or t.country = :country) and t.date = :date")
    List<Temperature> findByDateAndCityOrCountry(@Param("city") String city,
                                                 @Param("country") String country,
                                                 @Param("date") LocalDate date);

}
