package com.defaults.marketplace.apigateway.models.orders;

import java.time.LocalDateTime;

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

    public Weather(){}

    public Weather(LocalDateTime dateTime, String precipitation, String temp, String humidity, String weather) {
        this.dateTime = dateTime;
        this.precipitation = precipitation;
        this.temp = temp;
        this.humidity = humidity;
        this.weather = weather;
    }
}
