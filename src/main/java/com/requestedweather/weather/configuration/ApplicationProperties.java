package com.requestedweather.weather.configuration;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Data
@Validated
@Component
@ConfigurationProperties(prefix = "app")
public class ApplicationProperties {

    @Value("${app.weather.city}")
    private List<String> cityList;
}
