package de.maritimehack.scrapmap.backend.logic;

import de.maritimehack.scrapmap.backend.controller.SeaRoutesController;
import de.maritimehack.scrapmap.backend.pojo.SeaRoutesWeatherPojo;

import java.util.List;

public class PollutionPredictor {
    private SeaRoutesController seaRoutesController = new SeaRoutesController();

    public void calcPosition(Float lat, Float lng) {
        List<SeaRoutesWeatherPojo> weatherData = seaRoutesController.getWeatherData(lat, lng);

        if (weatherData != null) {
            weatherData.forEach(System.out::println);

            weatherData.
        }
    }
}