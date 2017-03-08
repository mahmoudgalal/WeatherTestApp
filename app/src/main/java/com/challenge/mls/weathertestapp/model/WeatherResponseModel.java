package com.challenge.mls.weathertestapp.model;

import com.challenge.mls.weathertestapp.Constants;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by fujitsu-lap on 08/03/2017.
 *
 * Example response :
 *
 * {"coord":
 {"lon":145.77,"lat":-16.92},
 "weather":[{"id":803,"main":"Clouds","description":"broken clouds","icon":"04n"}],
 "base":"cmc stations",
 "main":{"temp":293.25,"pressure":1019,"humidity":83,"temp_min":289.82,"temp_max":295.37},
 "wind":{"speed":5.1,"deg":150},
 "clouds":{"all":75},
 "rain":{"3h":3},
 "dt":1435658272,
 "sys":{"type":1,"id":8166,"message":0.0166,"country":"AU","sunrise":1435610796,"sunset":1435650870},
 "id":2172797,
 "name":"Cairns",
 "cod":200}

 */
public class WeatherResponseModel {
    public Coordinates getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(Coordinates coordinates) {
        this.coordinates = coordinates;
    }

    public List<Weather> getWeather() {
        return weather;
    }

    public void setWeather(List<Weather> weather) {
        this.weather = weather;
    }

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public MainWeatherData getMainWeatherData() {
        return mainWeatherData;
    }

    public void setMainWeatherData(MainWeatherData mainWeatherData) {
        this.mainWeatherData = mainWeatherData;
    }

    public  String getIconUrl(){
        return Constants.IMG_DATA_BASE_URL+"/img/w/"+weather.get(0).icon+".png";
    }

    @SerializedName("coord")
    private Coordinates coordinates;
    private List<Weather> weather;
    String base ;
    @SerializedName("name")
    String cityName;
    @SerializedName("main")
    MainWeatherData mainWeatherData;



    public static class Coordinates{
        double lon;
        double lat;
    }
    public static  class Weather{
        int id;
        String main;
        String description;
        String icon;
    }
    public  static  class MainWeatherData{
        public double getTemp() {
            return temp;
        }

        public void setTemp(double temp) {
            this.temp = temp;
        }

        public double getPressure() {
            return pressure;
        }

        public void setPressure(double pressure) {
            this.pressure = pressure;
        }

        public double getHumidity() {
            return humidity;
        }

        public void setHumidity(double humidity) {
            this.humidity = humidity;
        }

        double temp;
        double pressure;
        double humidity;

    }
}
