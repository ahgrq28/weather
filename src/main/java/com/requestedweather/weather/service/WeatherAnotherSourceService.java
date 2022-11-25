package com.requestedweather.weather.service;

import com.requestedweather.weather.entity.Temperature;
import com.requestedweather.weather.interaction.Temp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public interface WeatherAnotherSourceService {

    default List<Temperature> findTemperatureAnotherSource() {
        List<String> cityList = getCityList();
        if (cityList.isEmpty()) {
            return Collections.emptyList();
        }
        List<Temperature> temperatureList = new ArrayList<>();

        for (String city : cityList) {
            Temp temp = getWeather(city);
            Temperature temperature = Temperature.builder()
                    .city(city)
                    .currentTemperature(temp.getValue())
                    .date(LocalDate.now())
                    .build();
            temperatureList.add(temperature);
        }

        return temperatureList;
    }

    List<String> getCityList();

    Temp getWeather(String city);
}
