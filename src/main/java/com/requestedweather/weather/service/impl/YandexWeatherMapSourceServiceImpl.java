package com.requestedweather.weather.service.impl;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.requestedweather.weather.entity.Temperature;
import com.requestedweather.weather.service.WeatherAnotherSourceService;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class YandexWeatherMapSourceServiceImpl implements WeatherAnotherSourceService {

    private static final String URL_ADRESS = "https://api.weather.yandex.ru/v2/forecast?lat=%s&lon=%s&extra=true";
    private static final String KEY_NAME = "X-Yandex-API-Key";

    @Value("${yandex.apiKey}")
    private String apiKey;

    @Value("${weather.city}")
    private List<String> cityList;

    private final FiasService fiasService;

    @Autowired
    public YandexWeatherMapSourceServiceImpl(FiasService fiasService) {
        this.fiasService = fiasService;
    }

    @Override
    public List<Temperature> findTemperatureAnotherSource() {
        if (cityList.isEmpty()) {
            return Collections.emptyList();
        }

        List<Temperature> temperatureList = new ArrayList<>();

        for (String city : cityList) {
            String geoPositionByCity = fiasService.getGeoPositionByCity(city);
            Double temperatureByCity = getTemperatureByCity(geoPositionByCity);
            Temperature temperature = new Temperature()
                    .setCity(city)
                    .setCurrentTemperature(temperatureByCity)
                    .setDate(LocalDate.now());
            temperatureList.add(temperature);
        }

        return temperatureList;
    }

    protected Double getTemperatureByCity(String geoPosition) {
        if (geoPosition.isEmpty()) {
            throw new RuntimeException("Geo position not found");
        }
        String outPutDataTemperature = getDataTemperatureByCity(geoPosition);
        if (outPutDataTemperature.isEmpty()) {
            throw new RuntimeException("Server YandexWeather is not available");
        }

        Double temperature = null;
        try {
            JSONObject jsonObject = new JSONObject(outPutDataTemperature);
            temperature = jsonObject.getJSONObject("yesterday").getDouble("temp");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        if (temperature == null) {
            throw new RuntimeException("The server YandexWeather returned an invalid value");
        }

        return Double.valueOf(temperature);
    }

    protected String getDataTemperatureByCity(String geoPosition) {
        String[] geoPositionMas = geoPosition.split(",");
        StringBuilder contentYandexWeather = new StringBuilder();

        try {
            URL url = new URL(String.format(URL_ADRESS, geoPositionMas[0], geoPositionMas[1]));
            URLConnection urlConnection = url.openConnection();
            urlConnection.setRequestProperty(KEY_NAME, apiKey);

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                contentYandexWeather.append(line)
                        .append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException("Service YandexWeather do not connect" + e);
        }
        return contentYandexWeather.toString();
    }


}
