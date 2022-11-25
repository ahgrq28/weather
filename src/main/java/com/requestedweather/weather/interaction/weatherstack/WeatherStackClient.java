package com.requestedweather.weather.interaction.weatherstack;

import com.requestedweather.weather.interaction.weatherstack.dto.response.WeatherStackResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface WeatherStackClient {

    @GetMapping("/")
    WeatherStackResponse getWeather(@RequestParam(name = "access_key") String accessKey,
                                    @RequestParam(name = "query") String query);
}
