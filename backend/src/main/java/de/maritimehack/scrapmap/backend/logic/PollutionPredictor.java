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

    public Double[] getNextPosition(Double currentLat, Double currentLong, Double time, Double bearing) {
/*        Double distance = velocity * time;

        long radiusOfEarth = 6371000l;
        Double angularDistance = distance/radiusOfEarth;
        Double bearingRad = toRadians(bearing);

        Double latRad = toRadians(currentLat);
        Double lonRad = toRadians(currentLong);

        Double newLat = Math.sin(latRad)*Math.sin(latRad) + Math.sin(latRad)*Math.sin(angularDistance)*Math.cos(bearingRad);
        Double newLatFinal = Math.asin(newLat);
        Double newLon = lonRad + Math.atan2(Math.sin(bearingRad) * Math.sin(angularDistance) * Math.cos(latRad), Math.cos(angularDistance) - Math.sin(latRad) * Math.sin(newLat));

        return new Double[]{toDegrees(newLatFinal), (toDegrees(newLon)+540)%360-180};*/
return null;
    }


    public double toRadians(double degrees)
    {
        return degrees * (Math.PI / 180);
    }

    public double toDegrees(double radians)
    {
        return radians * 180 / Math.PI;
    }

    public double toBearing(double radians) {
        // convert radians to degrees (as bearing: 0...360)
        return (toDegrees(radians) + 360) % 360;
    }
}