package de.maritimehack.scrapmap.backend.logic;

import com.google.gson.Gson;
import de.maritimehack.scrapmap.backend.controller.SeaRoutesController;
import de.maritimehack.scrapmap.backend.pojo.SeaRoutesWeatherPojo;
import jdk.nashorn.internal.parser.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;

public class PollutionPredictor {
    @Autowired
    private SeaRoutesController seaRoutesController;

    public void calcPosition(Float lat, Float lng) {
/*        JSONParser parser = new JSONParser();

        String data = seaRoutesController.getWeatherData(lat, lng);
        SeaRoutesWeatherPojo weatherData = parser.parse(data);
        System.out.println(weatherData);*/
    }
}
