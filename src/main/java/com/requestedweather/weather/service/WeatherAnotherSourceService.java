package com.requestedweather.weather.service;

import java.util.List;

import com.requestedweather.weather.entity.Temperature;

public interface WeatherAnotherSourceService {

    List<Temperature> findTemperatureAnotherSource();
}
