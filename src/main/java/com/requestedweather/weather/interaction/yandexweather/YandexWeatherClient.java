package com.requestedweather.weather.interaction.yandexweather;

import com.requestedweather.weather.interaction.yandexweather.dto.response.YandexWeatherResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

public interface YandexWeatherClient {

    @GetMapping("/")
    YandexWeatherResponse getWeather(@RequestParam(name = "lat") String lat,
                                     @RequestParam(name = "lon") String lon,
                                     @RequestParam(name = "extra") boolean extra);
}
