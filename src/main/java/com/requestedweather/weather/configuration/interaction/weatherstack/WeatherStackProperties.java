package com.requestedweather.weather.configuration.interaction.weatherstack;

import com.requestedweather.weather.interaction.openweather.OpenWeatherClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

/**
 * Конфигурация {@link  OpenWeatherClient}
 */
@Data
@Validated
@Component
@ConfigurationProperties("app.interaction.weather-stack")
public class WeatherStackProperties {

    @NotBlank
    private String url;

    @NotBlank
    private String accessKey;

    @Positive
    @NotNull
    private Integer connectionTimeout;

    @Positive
    @NotNull
    private Integer readTimeout;

    private boolean feignLogsEnabled;
}
