package com.requestedweather.weather.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Temperature {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    @Column(name = "ID", nullable = false)
    private UUID id;
    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @Column(name = "CURRENT_TEMPERATURE", nullable = false)
    private Double currentTemperature;

    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    public UUID getId() {
        return id;
    }

    public Temperature setId(UUID id) {
        this.id = id;
        return this;
    }

    public String getCity() {
        return city;
    }

    public Temperature setCity(String city) {
        this.city = city;
        return this;
    }

    public String getCountry() {
        return country;
    }

    public Temperature setCountry(String country) {
        this.country = country;
        return this;
    }

    public Double getCurrentTemperature() {
        return currentTemperature;
    }

    public Temperature setCurrentTemperature(Double currentTemperature) {
        this.currentTemperature = currentTemperature;
        return this;
    }

    public LocalDate getDate() {
        return date;
    }

    public Temperature setDate(LocalDate date) {
        this.date = date;
        return this;
    }
}
