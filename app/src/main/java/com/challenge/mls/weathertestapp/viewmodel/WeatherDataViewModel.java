package com.challenge.mls.weathertestapp.viewmodel;

import android.content.Context;

import com.challenge.mls.weathertestapp.Constants;
import com.challenge.mls.weathertestapp.model.WeatherResponseModel;
import com.challenge.mls.weathertestapp.network.interfaces.NetworkManager;
import com.challenge.mls.weathertestapp.network.interfaces.WeatherDataService;

import retrofit.Callback;
import retrofit.Response;
import retrofit.Retrofit;

/**
 * Created by fujitsu-lap on 08/03/2017.
 */
public class WeatherDataViewModel {

    private OnDataUpdated listener;
    private  Context context;
    private NetworkManager networkManager;
    String currentCity ;
    public WeatherDataViewModel(Context context,String city,OnDataUpdated listener){
        this.listener = listener;
        this.context = context;
        currentCity= city;
        networkManager =  NetworkManager.getInstance();
    }

    /**
     * Life-cycle
     * Initialization goes here
     */
    public void onStart(){
        if(currentCity !=  null)
            update(currentCity);
    }

    public void update(String city){
        currentCity = city;
       WeatherDataService service = networkManager.getWeatherDataService();
        service.getWeatherData(city, Constants.APP_ID).enqueue(new Callback<WeatherResponseModel>() {
            @Override
            public void onResponse(Response<WeatherResponseModel> response, Retrofit retrofit) {
                if(listener != null)
                    listener.dataUpdated(response.body());
            }

            @Override
            public void onFailure(Throwable t) {
                if(listener != null)
                    listener.dataUpdated(null);
            }
        });
    }

    /**
     * Life-cycle
     * De initialization goes hear
     */
    public void onStop(){

    }

    public interface OnDataUpdated{
        void dataUpdated(WeatherResponseModel model);
    }
}
