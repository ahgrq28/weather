package com.requestedweather.weather.controller;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

import com.requestedweather.weather.dto.TemperatureDto;
import com.requestedweather.weather.entity.Temperature;
import com.requestedweather.weather.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {
    private static final String PATTERN = "dd-MM-yyyy";

    private final DateTimeFormatter formatter;

    private final TemperatureService temperatureService;

    @Autowired
    public WeatherController(TemperatureService temperatureService) {
        this.temperatureService = temperatureService;
        this.formatter = DateTimeFormatter.ofPattern(PATTERN);
    }

    @GetMapping("/")
    public ResponseEntity<List<TemperatureDto>> getWeatherByDate(@RequestParam(name = "city") String city,
                                                                 @RequestParam(name = "country") String country,
                                                                 @RequestParam(name = "date") String date) {
        LocalDate dateParse = LocalDate.parse(date, formatter);
        List<TemperatureDto> TemperatureDtoList = temperatureService
                .retrieveTemperature(city, country, dateParse).stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());
        return new ResponseEntity<>(TemperatureDtoList, HttpStatus.OK);
    }

    private TemperatureDto convertToDto(Temperature temperature) {
        return new TemperatureDto()
                .setCity(temperature.getCity())
                .setCountry(temperature.getCountry())
                .setCurrentTemperature(temperature.getCurrentTemperature())
                .setDate(temperature.getDate());
    }
}
