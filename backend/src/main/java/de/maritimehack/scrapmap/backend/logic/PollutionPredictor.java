package de.maritimehack.scrapmap.backend.logic;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.maritimehack.scrapmap.backend.controller.SeaRoutesController;
import de.maritimehack.scrapmap.backend.pojo.SeaRoutesWeatherPojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.*;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PollutionPredictor {
    private SeaRoutesController seaRoutesController = new SeaRoutesController();

    public void calcPosition(Float lat, Float lng) {
        List<SeaRoutesWeatherPojo> weatherData = seaRoutesController.getWeatherData(lat, lng);

        if (weatherData != null)
            weatherData.forEach(System.out::println);
    }
}
