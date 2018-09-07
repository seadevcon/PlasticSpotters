package de.maritimehack.scrapmap.backend.controller;

import javax.net.ssl.HttpsURLConnection;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

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
        return getJSON(url);
    }

    public String getWeatherData(Float lat, Float lng) {
        String url = BASE_URL + "ws/weather/stat/?lat=" + String.valueOf(lat) + "&lon=" + String.valueOf(lng);
        return getJSON(url);
    }

    public String getJSON(String url) {
        try (ByteArrayOutputStream baos = new ByteArrayOutputStream()) {
            HttpsURLConnection con = (HttpsURLConnection) new URL(url).openConnection();

            con.setRequestProperty("x-api-key", MY_API_KEY_HERE);
            con.setRequestProperty("accept", "application/json");

            byte[] buffer = new byte[4096];
            InputStream is = con.getInputStream();
            for (int nRead = is.read(buffer); nRead != -1; nRead = is.read(buffer)) {
                baos.write(buffer, 0, nRead);
            }
            System.out.print(new String(baos.toByteArray(), "UTF8"));
            return new String(baos.toByteArray(), "UTF8");
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
