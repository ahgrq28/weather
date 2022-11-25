package com.requestedweather.weather.service.impl;

import com.requestedweather.weather.configuration.ApplicationProperties;
import com.requestedweather.weather.configuration.interaction.openweather.OpenWeatherProperties;
import com.requestedweather.weather.interaction.Temp;
import com.requestedweather.weather.interaction.openweather.OpenWeatherClient;
import com.requestedweather.weather.service.WeatherAnotherSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OpenWeatherMapSourceServiceImpl implements WeatherAnotherSourceService {

    private final OpenWeatherClient openWeatherClient;
    private final ApplicationProperties appProperties;
    private final OpenWeatherProperties properties;

    @Override
    public List<String> getCityList() {
        return appProperties.getCityList();
    }

    @Override
    public Temp getWeather(String city) {
        return openWeatherClient.getWeather(
                properties.getApiKey(), city, properties.getUnits());
    }
}
