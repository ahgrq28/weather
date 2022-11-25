package com.requestedweather.weather.shedule;

import com.requestedweather.weather.service.TemperatureService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class WeatherScheduled {

    public static final String UPDATE_CURRENCY_FROM_CBR_CRON = "0 1 1 * * ?";
    private final TemperatureService temperatureService;

    @Scheduled(cron = UPDATE_CURRENCY_FROM_CBR_CRON)
    public void loadWeatherData() {

        log.info("Try to load weather data");
        temperatureService.updateDataWeather();
    }
}
