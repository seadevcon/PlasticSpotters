package de.maritimehack.scrapmap.backend.controller;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import de.maritimehack.scrapmap.backend.pojo.SeaRoutesWeatherPojo;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.List;

public class SeaRoutesController {
    private final String MY_API_KEY_HERE = "Eq2MMoXzsI7uW1EGjYi2l6vvpJArfaQN8Cd73z4d";
    // base url for the subsequent requests
    private final String BASE_URL = "https://api.searoutes.com/";

    public String pointBuilder(Float lat, Float lng) {
        return "lon:" + lng + "lat:" + lat;
    }

    public String latBuilder(Float lat) {
        return "lat:" + lat;
    }

    public String lngBuilder(Float lng) {
        return "lon:" + lng;
    }

    public String getSOme(Float[] from_point, Float[] to_point) {
        String url = BASE_URL + "/intermodal/route/" + pointBuilder(from_point[0], from_point[1]) + '/' + pointBuilder(to_point[0], from_point[1]);
        //return getJSON(url);
        return "";
    }

    public List<SeaRoutesWeatherPojo> getWeatherData(Float lat, Float lng) {
        String url = BASE_URL + "ws/weather/stat/?lat=" + String.valueOf(lat) + "&lon=" + String.valueOf(lng);

        ObjectMapper objectMapper = new ObjectMapper();
        try {
            TypeFactory typeFactory = objectMapper.getTypeFactory();
            CollectionType collectionType = typeFactory.constructCollectionType(
                    List.class, SeaRoutesWeatherPojo.class);
            return objectMapper.readValue(getJSON(url), collectionType);
        } catch (JsonParseException e) {
            e.printStackTrace();
        } catch (JsonMappingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public InputStream getJSON(String url) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();

            con.setRequestProperty("x-api-key", MY_API_KEY_HERE);
            con.setRequestProperty("accept", "application/json");

            byte[] buffer = new byte[4096];
            return con.getInputStream();
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
