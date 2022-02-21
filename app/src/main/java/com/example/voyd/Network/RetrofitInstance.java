package com.example.voyd.Network;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    public static String BASE_URL="https://vyod.manaknightdigital.com/";
    private static Retrofit retrofit;
    public static Retrofit getRetrofitClient(){
        if (retrofit==null){
            retrofit =new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).build();

        }
        return retrofit;
    }
}
