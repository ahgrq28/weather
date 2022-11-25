package com.requestedweather.weather.dto;

import com.requestedweather.weather.entity.Temperature;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.Nullable;
import java.time.LocalDate;

/**
 * Сущность прослойка между рест и базой данных.
 * Хранит в себе город, страну и его температура.
 */
@Builder
@Getter
@Setter
public class TemperatureDto {

    @NotNull
    private String city;

    @Nullable
    private String country;

    @NotNull
    private Double currentTemperature;

    @NotNull
    private LocalDate date;

    public static TemperatureDto convertTo(Temperature temperature) {

        return TemperatureDto.builder()
                .city(temperature.getCity())
                .country(temperature.getCountry())
                .currentTemperature(temperature.getCurrentTemperature())
                .date(temperature.getDate())
                .build();
    }
}
