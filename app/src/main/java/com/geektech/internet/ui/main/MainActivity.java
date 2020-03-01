package com.geektech.internet.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.geektech.internet.R;
import com.geektech.internet.data.internet.RetrofitBuilder;
import com.geektech.internet.data.pojo.CurrentWeather;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    private TextView tvCurrentWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        loadWeather();
    }

    private void loadWeather() {
        RetrofitBuilder
                .getService()
                .getCurrentWeather("Bishkek", "4bbf5a1ed98cd9f688ebb3651474cdd2")
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null){
                            CurrentWeather weather = response.body();
                            tvCurrentWeather.setText(weather.getMain().getTemp().toString());
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {

                    }
                });
    }

    private void setupViews() {
        tvCurrentWeather = findViewById(R.id.tvCurrentWeather);
    }
}
