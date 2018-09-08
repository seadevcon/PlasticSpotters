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

import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class PollutionPredictor {
    @Autowired
    private SeaRoutesController seaRoutesController;

    public void calcPosition(Float lat, Float lng) {
        String data = seaRoutesController.getWeatherData(lat, lng);
        List<SeaRoutesWeatherPojo> weatherData = new LinkedList<>();

        ObjectMapper mapper = new ObjectMapper();

        try {
            JsonFactory factory = new JsonFactory();
            JsonParser parser = factory.createParser(data);

            Iterator<SeaRoutesWeatherPojo> it = mapper.readValues(parser, SeaRoutesWeatherPojo.class);

            while (it.hasNext()) {
                weatherData.add(it.next());
            }

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

        weatherData.forEach(System.out::print);

    }
}
