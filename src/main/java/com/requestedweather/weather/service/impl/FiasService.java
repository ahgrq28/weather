package com.requestedweather.weather.service.impl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

@Service
public class FiasService {
    private static final Map<String, String> geoCityMap = new HashMap<>();

    public FiasService() {
        geoCityMap.put("Moscow", "55.75396,37.620393");
        geoCityMap.put("Berlin", "52.5243700,13.4105300");
    }

    public String getGeoPositionByCity(String city){
        return geoCityMap.get(city);
    }
}
