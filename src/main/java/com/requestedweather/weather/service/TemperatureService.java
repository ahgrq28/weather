package com.requestedweather.weather.service;

import java.time.LocalDate;
import java.util.List;

import com.requestedweather.weather.entity.Temperature;

public interface TemperatureService {

    List<Temperature> retrieveTemperature(String city, String country, LocalDate date);

    void saveTemperature(List<Temperature> temperatureList);
}
