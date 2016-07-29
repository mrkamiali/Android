package com.android.jsonrequestproj;

import com.android.jsonrequestproj.DataModel.Weather;
import com.google.gson.Gson;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Created by Kamran ALi on 7/30/2016.
 */
public interface WeatherAPi {
    public static final String Base_URL = "https://query.yahooapis.com/v1/public/";

    @GET("yql?q=select%20*%20from%20weather.forecast%20where%20woeid%20in%20(select%20woeid%20from%20geo.places(1)%20where%20text%3D%22Pakistan%2C%20Karachi%22)&format=json&env=store%3A%2F%2Fdatatables.org%2Falltableswithkeys")
    Call<Weather> getWeather();


    class Factory {
        private static WeatherAPi service;

        public static WeatherAPi getInstance() {

            if (service == null) {
                Retrofit retrofit = new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl(Base_URL)
                        .build();

                service = retrofit.create(WeatherAPi.class);
                return service;
            } else {
                return service;
            }
        }
    }

}
