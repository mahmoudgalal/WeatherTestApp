package com.challenge.mls.weathertestapp.network.interfaces;

import com.challenge.mls.weathertestapp.model.WeatherResponseModel;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Query;


/**
 * Created by fujitsu-lap on 08/03/2017.
 */
public interface WeatherDataService {
    @GET("data/2.5/weather")
    Call<WeatherResponseModel> getWeatherData(@Query("q") String cityName,@Query("appid") String appId);
}
