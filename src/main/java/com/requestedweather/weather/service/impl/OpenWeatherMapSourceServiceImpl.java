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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class OpenWeatherMapSourceServiceImpl implements WeatherAnotherSourceService {
    private static final String URL_ADRESS = "http://api.openweathermap.org/data/2.5/weather" +
            "?APPID=6cbc1fe633068ce37e221d06027a756b&q=%s&units=metric";

    @Value("${weather.city}")
    private List<String> cityList;

    @Override
    public List<Temperature> findTemperatureAnotherSource() {
        if (cityList.isEmpty()) {
            return Collections.emptyList();
        }
        List<Temperature> temperatureList = new ArrayList<>();

        for (String city : cityList) {
            Double temperatureByCity = getTemperatureByCity(city);
            Temperature temperature = new Temperature()
                    .setCity(city)
                    .setCurrentTemperature(temperatureByCity)
                    .setDate(LocalDate.now());
            temperatureList.add(temperature);
        }

        return temperatureList;
    }

    protected Double getTemperatureByCity(String city) {
        String outPutDataTemperature = getDataTemperatureByCity(city);
        if (outPutDataTemperature.isEmpty()) {
            throw new RuntimeException("Server openWeatherMap is not available");
        }

        Double temperature = null;
        try {
            JSONObject jsonObject = new JSONObject(outPutDataTemperature);
            temperature = jsonObject.getJSONObject("main").getDouble("temp");
        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        if (temperature == null) {
            throw new RuntimeException("The server openWeatherMap returned an invalid value");
        }

        return Double.valueOf(temperature);
    }


    protected String getDataTemperatureByCity(String city) {
        StringBuilder content = new StringBuilder();

        try {
            URL url = new URL(String.format(URL_ADRESS, city));
            URLConnection urlConnection = url.openConnection();

            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
            String line;

            while ((line = bufferedReader.readLine()) != null) {
                content.append(line)
                        .append("\n");
            }

        } catch (IOException e) {
            throw new RuntimeException("Service do not connect" + e);
        }
        return content.toString();
    }


}
