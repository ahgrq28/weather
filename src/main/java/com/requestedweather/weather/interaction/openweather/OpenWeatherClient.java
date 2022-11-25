package com.requestedweather.weather.interaction.openweather;

import com.requestedweather.weather.interaction.openweather.dto.response.OpenWeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface OpenWeatherClient {

    @GetMapping("/")
    OpenWeatherResponse getWeather(@RequestParam(name = "APPID") String appId,
                                       @RequestParam(name = "q") String q,
                                       @RequestParam(name = "units") String units);
}
