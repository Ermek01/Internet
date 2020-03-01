package com.geektech.internet.data.internet;

import com.geektech.internet.data.pojo.CurrentWeather;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

    @GET("data/2.5/weather")
    Call<CurrentWeather> getCurrentWeather(@Query("q") String city, @Query("appid") String appId);

}
