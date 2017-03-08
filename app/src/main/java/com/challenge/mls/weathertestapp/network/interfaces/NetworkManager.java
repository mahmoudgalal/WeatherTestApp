package com.challenge.mls.weathertestapp.network.interfaces;

import com.challenge.mls.weathertestapp.Constants;

import retrofit.GsonConverterFactory;
import retrofit.Retrofit;


/**
 * Created by fujitsu-lap on 08/03/2017.
 */
public class NetworkManager {
    private static NetworkManager instance;
    private  NetworkManager() {}

    public static synchronized NetworkManager getInstance(){
        if (instance == null)
            instance= new NetworkManager();
        return instance;
    }

    public WeatherDataService getWeatherDataService(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.WEATHER_DATA_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        return  retrofit.create(WeatherDataService.class);
    }
}
