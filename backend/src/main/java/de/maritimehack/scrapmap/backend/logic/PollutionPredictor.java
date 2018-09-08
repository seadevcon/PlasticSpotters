package de.maritimehack.scrapmap.backend.logic;

import de.maritimehack.scrapmap.backend.controller.SeaRoutesController;
import de.maritimehack.scrapmap.backend.pojo.SeaRoutesWeatherPojo;

import java.util.List;

public class PollutionPredictor {
    private SeaRoutesController seaRoutesController = new SeaRoutesController();

    /**
     *
     * @param lat last known lat
     * @param lng last known long
     * @param duration duration in s
     */
    public void calcPosition(Double lat, Double lng, Float duration) {
        List<SeaRoutesWeatherPojo> weatherData = seaRoutesController.getWeatherData(lat, lng);

        if (weatherData != null) {
            weatherData.forEach(System.out::println);
            weatherData.forEach(weatherDatum -> System.out.println(weatherDatum.getWind_u_mean()));
        }
    }

    public Double[] getNextPosition(Double currentLat, Double currentLong, Double time, Double angle) {
        //distance = speed * time
        //we want to calc only the next x sec cause we need to know the next speed vector

        //Double currentLatRad =
        return null;
    }


    public static double ToRad(double degrees)
    {
        return degrees * (Math.PI / 180);
    }

    public static double ToDegrees(double radians)
    {
        return radians * 180 / Math.PI;
    }

    public static double ToBearing(double radians) {
        // convert radians to degrees (as bearing: 0...360)
        return (ToDegrees(radians) + 360) % 360;
    }
}