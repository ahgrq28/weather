package com.requestedweather.weather.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.requestedweather.weather.entity.Temperature;
import com.requestedweather.weather.repository.TemperatureRepository;
import com.requestedweather.weather.service.TemperatureService;
import com.requestedweather.weather.service.WeatherAnotherSourceService;
import jdk.nashorn.internal.runtime.logging.Logger;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TemperatureServiceImpl implements TemperatureService {

    private final TemperatureRepository temperatureRepository;

    private final List<WeatherAnotherSourceService> weatherAnotherSourceServices;

    @Override
    public List<Temperature> retrieveTemperature(String city, String country, LocalDate date) {

        List<Temperature> temperatureListByDate = temperatureRepository
                .findByDateAndCityOrCountry(city, country, date);

        if (temperatureListByDate.isEmpty()) {
            return temperatureRepository.findByDateAndCityOrCountry(city, country, LocalDate.now());
        }

        return temperatureListByDate;
    }

    @Override
    public void saveTemperature(List<Temperature> temperatureList) {

        temperatureRepository.saveAll(temperatureList);
    }

    @Override
    public void updateDataWeather() {

        List<Temperature> temperatureAnotherSource = new ArrayList<>();
        for (WeatherAnotherSourceService weatherAnotherSourceService : weatherAnotherSourceServices) {
            try {
                List<Temperature> temperatureAnotherList = weatherAnotherSourceService
                        .findTemperatureAnotherSource();
                mergeTemperature(temperatureAnotherSource, temperatureAnotherList);
            } catch (RuntimeException e) {
                log.debug("Failed to retrieve data from server " + weatherAnotherSourceService.getClass());
            }
        }

        saveTemperature(temperatureAnotherSource);
    }

    private void mergeTemperature(List<Temperature> temperatureAnotherSource,
                                  List<Temperature> temperatureAnotherList) {

        if (temperatureAnotherSource.isEmpty()) {
            temperatureAnotherSource.addAll(temperatureAnotherList);
            return;
        }

        for (Temperature temperature : temperatureAnotherSource) {
            for (Temperature temperatureAnother : temperatureAnotherList) {
                if (temperatureAnother.getCity().equals(temperature.getCity())) {
                    Double temp = (temperatureAnother.getCurrentTemperature()
                            + temperature.getCurrentTemperature()) / 2;
                    temperature.setCurrentTemperature(temp);
                }
            }
        }
    }
}
