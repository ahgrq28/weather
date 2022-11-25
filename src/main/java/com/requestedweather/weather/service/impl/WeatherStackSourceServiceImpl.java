package com.requestedweather.weather.service.impl;

import com.requestedweather.weather.configuration.ApplicationProperties;
import com.requestedweather.weather.configuration.interaction.weatherstack.WeatherStackProperties;
import com.requestedweather.weather.interaction.Temp;
import com.requestedweather.weather.interaction.weatherstack.WeatherStackClient;
import com.requestedweather.weather.service.WeatherAnotherSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WeatherStackSourceServiceImpl implements WeatherAnotherSourceService {

    private final ApplicationProperties appProperties;
    private final WeatherStackClient weatherStackClient;
    private final WeatherStackProperties weatherStackProperties;

    @Override
    public List<String> getCityList() {

        return appProperties.getCityList();
    }

    @Override
    public Temp getWeather(String city) {

        return weatherStackClient.getWeather(weatherStackProperties.getAccessKey(), city);
    }
}
