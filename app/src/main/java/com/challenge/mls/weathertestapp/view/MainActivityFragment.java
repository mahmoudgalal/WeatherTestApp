package com.challenge.mls.weathertestapp.view;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.challenge.mls.weathertestapp.R;
import com.challenge.mls.weathertestapp.Utils;
import com.challenge.mls.weathertestapp.model.WeatherResponseModel;
import com.challenge.mls.weathertestapp.viewmodel.WeatherDataViewModel;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements WeatherDataViewModel.OnDataUpdated {

    private ProgressBar loadProgress;
    private TextView temperatureTxt;
    private TextView pressureTxt;
    private TextView humidtyTxt;
    private TextView dateTxt;
    private EditText cityTxt;
    private ImageView iconImageView;
    private Button goBtn;

    private WeatherDataViewModel viewModel;


    public MainActivityFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View root =  inflater.inflate(R.layout.fragment_main, container, false);
        loadProgress = (ProgressBar)root.findViewById(R.id.progressBar);
        temperatureTxt = (TextView)root.findViewById(R.id.temp);
        pressureTxt = (TextView)root.findViewById(R.id.pressure);
        humidtyTxt = (TextView)root.findViewById(R.id.humidity);
        dateTxt = (TextView)root.findViewById(R.id.date);
        cityTxt = (EditText)root.findViewById(R.id.cityText);
        goBtn =  (Button)root.findViewById(R.id.go_btn);
        iconImageView = (ImageView)root.findViewById(R.id.icon);

        goBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Utils.isInternetConnectionExist(view.getContext())) {
                    String cityName = cityTxt.getText().toString().trim();
                    if (!cityName.isEmpty()) {
                        loadProgress.setVisibility(View.VISIBLE);
                        viewModel.update(cityName);
                    } else
                        Toast.makeText(view.getContext(), "empty city name !!", Toast.LENGTH_LONG).show();

                } else {
                    Toast.makeText(view.getContext(), "No Internet Connection !!", Toast.LENGTH_LONG).show();
                }

            }
        });

        viewModel =  new WeatherDataViewModel(this.getActivity(),null,this);
        return  root;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        viewModel.onStart();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        viewModel.onStop();
    }
    private void bindData(WeatherResponseModel model){
        SimpleDateFormat  simpleDateFormat = new SimpleDateFormat("dd-MM-yyy hh:mm");
        dateTxt.setText("Date:"+ simpleDateFormat.format(new Date()));
        pressureTxt.setText("Pressure:"+model.getMainWeatherData().getPressure());
        temperatureTxt.setText("Temperature:"+model.getMainWeatherData().getTemp());
        humidtyTxt.setText("Humidity:"+model.getMainWeatherData().getHumidity());
        Glide.with(this.getActivity()).load(model.getIconUrl()).centerCrop().
                crossFade().placeholder(R.mipmap.placeholder).
                diskCacheStrategy(DiskCacheStrategy.ALL).into(iconImageView);

    }


    @Override
    public void dataUpdated(WeatherResponseModel model) {
        if(model != null){
            bindData(model);

        }else {
            Toast.makeText(this.getActivity(),"Error retrieving data !!",Toast.LENGTH_LONG).show();
        }
        if(loadProgress != null)
        loadProgress.setVisibility(View.GONE);
    }
}
