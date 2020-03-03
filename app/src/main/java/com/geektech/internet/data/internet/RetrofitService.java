package com.geektech.internet.data.internet;

import com.geektech.internet.data.pojo.CurrentWeather;
import com.geektech.internet.data.pojo.ForecastWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.geektech.internet.data.internet.ApiEndPoints.CURRENT_WEATHER;
import static com.geektech.internet.data.internet.ApiEndPoints.FORECAST_WEATHER;

public interface RetrofitService {

    @GET(CURRENT_WEATHER)
    Call<CurrentWeather> getCurrentWeather(
            @Query("q") String city,
            @Query("appid") String appId,
            @Query("units") String units);

    @GET(FORECAST_WEATHER)
    Call<ForecastWeather> getForecastWeather(
            @Query("q") String city,
            @Query("appid") String appId,
            @Query("units") String units);

}
