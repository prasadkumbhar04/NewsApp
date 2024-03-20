package com.example.newsapp;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiUtils {
private  static Retrofit retrofit = null;

    public static ApiInterface getApiInterface(){
        if(retrofit ==  null){
            // create retrofit obj
            retrofit = new Retrofit.Builder().baseUrl(ApiInterface.baseUrl).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit.create(ApiInterface.class);
    }

}
