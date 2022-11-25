package com.requestedweather.weather.interaction.yandexweather.dto.response;

import com.requestedweather.weather.interaction.Temp;
import lombok.Builder;
import lombok.Data;

import javax.annotation.concurrent.Immutable;

@Data
@Builder
@Immutable
public class YandexWeatherResponse implements Temp {

    public final Yesterday yesterday;

    @Override
    public Double getValue() {
        return yesterday != null ? yesterday.temp : null;
    }

    @Data
    @Builder
    @Immutable
    public static class Yesterday {

        public Double temp;
    }
}
