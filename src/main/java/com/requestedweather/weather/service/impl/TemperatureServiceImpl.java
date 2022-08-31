package com.requestedweather.weather.service.impl;

import java.time.LocalDate;
import java.util.List;

import com.requestedweather.weather.entity.Temperature;
import com.requestedweather.weather.repository.TemperatureRepository;
import com.requestedweather.weather.service.TemperatureService;
import org.springframework.stereotype.Service;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    private final TemperatureRepository temperatureRepository;

    public TemperatureServiceImpl(TemperatureRepository temperatureRepository) {
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public List<Temperature> retrieveTemperature(String city, String country, LocalDate date) {
        List<Temperature> temperatureListByDate = temperatureRepository
                .findByDateAndCityOrCountry(city, country, date);
        if (temperatureListByDate.isEmpty()) {
            return temperatureRepository
                    .findByDateAndCityOrCountry(city, country,
                            LocalDate.now());
        }

        return temperatureListByDate;
    }

    @Override
    public void saveTemperature(List<Temperature> temperatureList) {
        temperatureRepository.saveAll(temperatureList);
    }
}
