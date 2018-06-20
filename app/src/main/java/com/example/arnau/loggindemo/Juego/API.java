package com.example.arnau.loggindemo.Juego;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;

public class API {

    private static API instance;

    private static final String baseUrl = "http://147.83.7.204:8080/myapp/json";
    public static TrackAPI api;

    private API(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl)
                .addConverterFactory(ScalarsConverterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        api = retrofit.create(TrackAPI.class);
    };

    public static API getInstance(){
        if(instance==null)
            return new API();
        return instance;
    }

}
