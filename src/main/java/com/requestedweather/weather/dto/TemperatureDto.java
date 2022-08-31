package com.requestedweather.weather.dto;

/**
 * Сущность прослойка между рест и базой данных.
 * Хранит в себе город, страну и его температура.
 */
public class TemperatureDto {

    private String city;
    private String country;
    private Double currentTemperature;

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
}
