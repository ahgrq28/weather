package com.requestedweather.weather.dto;

import java.time.LocalDate;

/**
 * Сущность прослойка между рест и базой данных.
 * Хранит в себе город, страну и его температура.
 */
public class TemperatureDto {

    private String city;
    private String country;
    private Double currentTemperature;
    private LocalDate date;

    public String getCity() {
        return city;
    }

    public TemperatureDto setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public TemperatureDto setCountry(String country) {
        this.country = country;
        return this;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public TemperatureDto setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public TemperatureDto setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
