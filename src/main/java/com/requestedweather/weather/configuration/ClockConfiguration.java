package com.requestedweather.weather.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Clock;
import java.time.ZoneId;
import java.time.ZoneOffset;

@Configuration
@RequiredArgsConstructor
public class ClockConfiguration {

    @Bean
    public Clock clock() {

        return Clock.system(ZoneId.of(ZoneOffset.UTC.getId()));
    }
}
