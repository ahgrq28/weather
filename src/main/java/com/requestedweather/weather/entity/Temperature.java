package com.requestedweather.weather.entity;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.Tolerate;
import org.springframework.lang.NonNull;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Data
@Builder
public class Temperature {

    @Id
    @GeneratedValue
    @Column(name = "ID", nullable = false)
    private UUID id;

    @NonNull
    @Column(name = "CITY")
    private String city;

    @Column(name = "COUNTRY")
    private String country;

    @NonNull
    @Column(name = "CURRENT_TEMPERATURE", nullable = false)
    private Double currentTemperature;

    @NonNull
    @Column(name = "DATE", nullable = false)
    private LocalDate date;

    @Tolerate
    Temperature() {
    }
}
