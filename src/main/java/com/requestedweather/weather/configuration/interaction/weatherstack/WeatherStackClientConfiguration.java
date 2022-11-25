package com.requestedweather.weather.configuration.interaction.weatherstack;

import com.requestedweather.weather.interaction.weatherstack.WeatherStackClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.Logger;
import feign.Request;
import feign.Retryer;
import feign.codec.Decoder;
import feign.codec.Encoder;
import feign.slf4j.Slf4jLogger;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.cloud.openfeign.FeignClientsConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

@Configuration
@EnableFeignClients
@RequiredArgsConstructor
@Import(FeignClientsConfiguration.class)
public class WeatherStackClientConfiguration {

    private final WeatherStackProperties properties;
    private final Decoder decoder;
    private final Encoder encoder;
    private final Client feignClient;
    private final Contract contract;
    private final Retryer retryer;

    @Bean
    WeatherStackClient getWeatherStackClientt(){

        return Feign.builder()
                .decoder(decoder)
                .encoder(encoder)
                .client(feignClient)
                .contract(contract)
                .retryer(retryer)
                .requestInterceptor(requestTemplate ->
                        requestTemplate.header(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE))
                .options(new Request.Options(
                        properties.getConnectionTimeout(),
                        properties.getReadTimeout(),
                        false))
                .logger(new Slf4jLogger(WeatherStackClient.class))
                .logLevel(properties.isFeignLogsEnabled() ? Logger.Level.FULL : Logger.Level.NONE)
                .target(WeatherStackClient.class, properties.getUrl());
    }
}
