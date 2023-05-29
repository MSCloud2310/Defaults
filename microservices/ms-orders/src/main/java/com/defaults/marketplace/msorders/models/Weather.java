package com.defaults.marketplace.msorders.models;

import org.json.JSONArray;
import org.json.JSONObject;

import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class Weather {

    private LocalDateTime dateTime;
    private String precipitation;
    private String temp;
    private String humidity;
    private String weather;

    public String getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(String precipitation) {
        this.precipitation = precipitation;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Weather(LocalDateTime dateTime, String precipitation, String temp, String humidity, String weather) {
        this.dateTime = dateTime;
        this.precipitation = precipitation;
        this.temp = temp;
        this.humidity = humidity;
        this.weather = weather;
    }

    public static List<Weather> getWeathers(LocalDateTime dateFrom, LocalDateTime dateTo, String destination) throws IOException {
        List<Weather> weathers = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        String apiKey = "X5fUi0DxocH0iKETBLmjhaoRx4bGW83t";
        String apiUrl = "https://api.tomorrow.io/v4/timelines"+
                "?location=" + destination +
                "&fields=temperature,humidity,weatherCode,precipitationIntensity,precipitationType" +
                "&units=metric" +
                "&startTime=" + dateFrom.plusDays(1).format(formatter) +
                "&endTime=" + dateTo.plusDays(0).format(formatter) +
                "&timesteps=1d" +
                "&apikey=" + apiKey;

        URL url = new URL(apiUrl);

        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");

        int responseCode = conn.getResponseCode();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        StringBuilder response = new StringBuilder();
        while ((line = reader.readLine()) != null) {
            response.append(line);
        }
        reader.close();

        JSONObject jsonResponse = new JSONObject(response.toString());

        JSONObject data = new JSONObject();
        data = jsonResponse.getJSONObject("data");
        JSONArray timelinesArray = data.getJSONArray("timelines");
        JSONObject values = new JSONObject();
        JSONArray intervals = new JSONArray();
        for (Object timeline : timelinesArray){
            JSONObject jsonObject = (JSONObject) timeline;
             intervals = jsonObject.getJSONArray("intervals");
            for (Object interval : intervals){
                JSONObject intervalObject = (JSONObject) interval;
                values = intervalObject.getJSONObject("values");
                values.append("startTime", intervalObject.getString("startTime"));

                weathers.add(new Weather(
                        LocalDateTime.parse(values.getJSONArray("startTime").get(0).toString(), DateTimeFormatter.ISO_DATE_TIME),
                        String.valueOf(values.getDouble("precipitationIntensity")),
                        String.valueOf(values.getDouble("temperature")),
                        String.valueOf(values.getDouble("humidity")),
                        getWeatherDes(String.valueOf(values.getInt("weatherCode")))
                ));
            }
        }

        return weathers;
    }
    public static List<Item> addWeather(Cart cart) throws IOException {
        List<Item> items = cart.getItems();
        for (Item item : items) {
            if (item.getDateTo() == null && item.getDateFrom() == null) {
                item.setWeathers(null);
            } else {
                LocalDateTime currentDate = LocalDateTime.now();
                LocalDateTime limitDate = currentDate.plusDays(5);

                if (item.getDateFrom().isAfter(limitDate)) {
                    item.setWeathers(null);
                } else {
                    if(item.getDateTo().isAfter(limitDate)){
                        item.setWeathers(Weather.getWeathers(item.getDateFrom(), limitDate, item.getServiceDetails().getDestination()));
                    }else{
                        item.setWeathers(Weather.getWeathers(item.getDateFrom(), item.getDateTo(), item.getServiceDetails().getDestination()));
                    }
                }
            }
        }
        return items;
    }

    public static String getWeatherDes(String code){
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream("weatherCodes.json");
             JsonReader reader = Json.createReader(new InputStreamReader(inputStream))) {

            JsonObject codes = reader.readObject();

            JsonObject weatherCodes = codes.getJsonObject("weatherCode");

            try {
                return weatherCodes.getString(code);
            }catch (Exception e){
                return "Unknown";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
