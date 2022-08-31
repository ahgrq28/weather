package com.requestedweather.weather.shedule;

import java.util.ArrayList;
import java.util.List;

import com.requestedweather.weather.entity.Temperature;
import com.requestedweather.weather.service.TemperatureService;
import com.requestedweather.weather.service.WeatherAnotherSourceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class WeatherScheduled {
    private static final Logger LOGGER = LoggerFactory.getLogger(WeatherScheduled.class);
    public static final String UPDATE_CURRENCY_FROM_CBR_CRON = "0 1 1 * * ?";

    private final List<WeatherAnotherSourceService> weatherAnotherSourceServices;
    private final TemperatureService temperatureService;

    @Autowired
    public WeatherScheduled(List<WeatherAnotherSourceService> weatherAnotherSourceServices,
                            TemperatureService temperatureService) {
        this.weatherAnotherSourceServices = weatherAnotherSourceServices;
        this.temperatureService = temperatureService;
        //Добавлено для тестирования
        updateDataWeather();
    }

    @Scheduled(cron = UPDATE_CURRENCY_FROM_CBR_CRON)
    public void loadWeatherData() {
        LOGGER.info("Try to load weather data");
        updateDataWeather();
    }

    protected void updateDataWeather() {
        List<Temperature> temperatureAnotherSource = new ArrayList<>();
        for (WeatherAnotherSourceService weatherAnotherSourceService : weatherAnotherSourceServices) {
            try {
                List<Temperature> temperatureAnotherList = weatherAnotherSourceService
                        .findTemperatureAnotherSource();
                mergeTemperature(temperatureAnotherSource, temperatureAnotherList);
            } catch (RuntimeException e) {
                LOGGER.debug("Failed to retrieve data from server " + weatherAnotherSourceService.getClass());
            }
        }

        temperatureService.saveTemperature(temperatureAnotherSource);
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
