package com.example.restcasestudy.api;

import com.example.restcasestudy.model.StuffModel;

import java.util.List;

import retrofit.Call;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.Path;

/**
 * Created by topsykrett on 12/13/15.
 */
public interface StuffApi {

    @Headers({
            "Accept: application/json",
//            "User-Agent: Retrofit-Sample-App"
    })
    @GET("http://192.168.0.105:8080/WebApplication/webresources/com.example.mystuff/{stuff}")      //here is the other url part.best way is to start using /
    Call<StuffModel> getById(@Path("stuff") int stuff);

    @GET("/")      //here is the other url part.best way is to start using /
    Call<List<StuffModel>> getAll();

    }
