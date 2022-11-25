package com.requestedweather.weather.interaction.openweather.dto.response;

import com.requestedweather.weather.interaction.Temp;
import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.util.Assert;

import javax.annotation.concurrent.Immutable;

@Data
@Builder
@Immutable
public class OpenWeatherResponse implements Temp {

    public final MainResponse main;

    @NotNull
    private final String name;

    public static OpenWeatherResponseBuilder builder() {

        return new OpenWeatherResponseBuilder() {

            @Override
            public OpenWeatherResponse build() {

                Assert.notNull(super.name, "name == null");

                return super.build();
            }
        };
    }

    @Override
    public Double getValue() {
        return main != null ? main.temp : null;
    }

    @Data
    @Builder
    @Immutable
    public static class MainResponse {

        public Double temp;
    }
}


