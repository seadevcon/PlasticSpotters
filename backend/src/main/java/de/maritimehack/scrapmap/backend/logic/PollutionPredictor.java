package de.maritimehack.scrapmap.backend.logic;

import com.fasterxml.jackson.core.JsonGenerationException;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import de.maritimehack.scrapmap.backend.controller.SeaRoutesController;
import de.maritimehack.scrapmap.backend.pojo.SeaRoutesWeatherPojo;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

public class PollutionPredictor {
    @Autowired
    private SeaRoutesController seaRoutesController;

    public void calcPosition(Float lat, Float lng) {
        String data = seaRoutesController.getWeatherData(lat, lng);
        SeaRoutesWeatherPojo weatherData = null;

        ObjectMapper mapper = new ObjectMapper();

        try {
            weatherData = mapper.readValue(data, SeaRoutesWeatherPojo.class);
        } catch (JsonGenerationException e) {
            e.printStackTrace();
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (weatherData == null) {
            return;
        }

        System.out.print(weatherData);

    }
}
