package com.example.weatherupdate.Retrofit;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiInterface {

    @GET("weather?appid=75f84ec6c63ed4f4fdb1368649fdfdb7&units=metric")
    Call<Example> getWeatherData(@Query("q") String name);

}
