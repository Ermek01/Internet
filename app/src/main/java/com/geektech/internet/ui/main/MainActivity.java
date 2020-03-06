package com.geektech.internet.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.geektech.internet.R;
import com.geektech.internet.data.internet.RetrofitBuilder;
import com.geektech.internet.data.pojo.CurrentWeather;
import com.geektech.internet.data.pojo.ForecastWeather;
import com.geektech.internet.data.pojo.Main;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.geektech.internet.BuildConfig.APP_ID;

public class MainActivity extends AppCompatActivity {

    private TextView tvCurrentWeather, tvCityName, tvMax, tvMin, tvDesc;
    private RecyclerView recyclerView;
    private ForecastAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setupViews();
        setupRecyclerView();
        loadCurrentWeather();
        loadForecastWeather();
    }

    private void setupRecyclerView() {
        adapter = new ForecastAdapter();
        recyclerView.setAdapter(adapter);
    }

    private void loadForecastWeather() {
        RetrofitBuilder.getService().getForecastWeather(
                "Bishkek",
                APP_ID,
                "metric")
                .enqueue(new Callback<ForecastWeather>() {
                    @Override
                    public void onResponse(Call<ForecastWeather> call, Response<ForecastWeather> response) {
                        if (response.isSuccessful() && response.body() != null){
                            adapter.update(response.body().getList());
                        }
                    }

                    @Override
                    public void onFailure(Call<ForecastWeather> call, Throwable t) {
                        Log.d("sdasad", "aadadddsdssf");
                    }
                });
    }

    private void loadCurrentWeather() {
        RetrofitBuilder
                .getService()
                .getCurrentWeather("Bishkek", APP_ID, "metric")
                .enqueue(new Callback<CurrentWeather>() {
                    @Override
                    public void onResponse(Call<CurrentWeather> call, Response<CurrentWeather> response) {
                        if (response.isSuccessful() && response.body() != null){
                            CurrentWeather weather = response.body();
                            tvCurrentWeather.setText(
                                    getString(R.string.celcius,
                                            weather.getMain().getTemp().toString()));
                            tvCityName.setText(weather.getName() +  ", " + weather.getSys().getCountry());
                            tvMax.setText(weather.getMain().getTempMax().toString());
                            tvMin.setText(weather.getMain().getTempMin().toString());
                            tvDesc.setText(weather.getWeather().get(0).getDescription());
                        }
                        else {
                            Toast.makeText(MainActivity.this, "error", Toast.LENGTH_SHORT).show();
                        }
                    }

                    @Override
                    public void onFailure(Call<CurrentWeather> call, Throwable t) {
                        Toast.makeText(MainActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    private void setupViews() {
        tvCurrentWeather = findViewById(R.id.tvCurrentWeather);
        tvCityName = findViewById(R.id.tvCityName);
        recyclerView = findViewById(R.id.recyclerview);
        tvMax = findViewById(R.id.tvWeatherMax);
        tvMin = findViewById(R.id.tvWeatherMin);
        tvDesc = findViewById(R.id.tvDesc);
    }
}
