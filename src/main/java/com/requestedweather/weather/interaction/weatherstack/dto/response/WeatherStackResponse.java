package com.requestedweather.weather.interaction.weatherstack.dto.response;

import com.requestedweather.weather.interaction.Temp;
import lombok.Builder;
import lombok.Data;

import javax.annotation.concurrent.Immutable;

@Data
@Builder
@Immutable
public class WeatherStackResponse implements Temp {

    public final Current current;

    @Override
    public Double getValue() {
        return current != null ? current.temperature : null;
    }

    @Data
    @Builder
    @Immutable
    public static class Current {

        public Double temperature;
    }
}
