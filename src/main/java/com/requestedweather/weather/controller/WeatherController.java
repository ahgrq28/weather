package com.requestedweather.weather.controller;

import com.requestedweather.weather.dto.TemperatureDto;
import com.requestedweather.weather.entity.Temperature;
import com.requestedweather.weather.service.TemperatureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final TemperatureService temperatureService;

    @GetMapping("/")
    public ResponseEntity<List<TemperatureDto>> getWeatherByDate(@RequestParam(name = "city") String city,
                                                                 @RequestParam(name = "country") String country,
                                                                 @RequestParam(name = "date") String date) {

        LocalDate dateParse = LocalDate.from(DateTimeFormatter.ISO_LOCAL_DATE.parse(date));
        List<Temperature> temperatures = temperatureService.retrieveTemperature(city, country, dateParse);
        List<TemperatureDto> TemperatureDtoList = temperatures.stream()
                .map(TemperatureDto::convertTo)
                .collect(Collectors.toList());
        return new ResponseEntity<>(TemperatureDtoList, HttpStatus.OK);
    }

    @GetMapping("/update/date")
    public ResponseEntity<Void> updateWeatherDate() {

        temperatureService.updateDataWeather();
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
