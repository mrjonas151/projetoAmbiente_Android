package com.example.projeto4_mobile.network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {
    private static final String BASE_URL = "http://10.0.2.2:8085/";
    private static Retrofit retrofit;

    public static Retrofit getRetrofitInstance() {
         if (retrofit == null) {
             retrofit = new Retrofit.Builder()
                  .baseUrl(BASE_URL)
                  .addConverterFactory(GsonConverterFactory.create())
                  .build();
         }
         return retrofit;
    }
}