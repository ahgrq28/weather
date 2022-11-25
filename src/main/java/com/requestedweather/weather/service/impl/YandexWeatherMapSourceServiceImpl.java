package com.requestedweather.weather.service.impl;

import com.requestedweather.weather.configuration.ApplicationProperties;
import com.requestedweather.weather.interaction.Temp;
import com.requestedweather.weather.interaction.yandexweather.YandexWeatherClient;
import com.requestedweather.weather.service.WeatherAnotherSourceService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class YandexWeatherMapSourceServiceImpl implements WeatherAnotherSourceService {

    private final ApplicationProperties appProperties;
    private final YandexWeatherClient yandexWeatherClient;
    private final FiasService fiasService;


    @Override
    public List<String> getCityList() {

        return appProperties.getCityList();
    }

    @Override
    public Temp getWeather(String city) {

        String[] geoPositionMas = fiasService.getGeoPositionByCity(city).split(",");

        return yandexWeatherClient.getWeather(geoPositionMas[0], geoPositionMas[1], true);
    }
}
