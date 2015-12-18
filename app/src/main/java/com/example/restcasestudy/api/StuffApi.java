package com.example.restcasestudy.api;

import com.example.restcasestudy.model.StuffModel;

import java.util.List;

import retrofit.Call;
import retrofit.http.Body;
import retrofit.http.GET;
import retrofit.http.Headers;
import retrofit.http.POST;
import retrofit.http.PUT;
import retrofit.http.Path;

/**
 * Created by topsykrett on 12/13/15.
 */
public interface StuffApi {

    @Headers({
            "Accept: application/json",
//            "User-Agent: Retrofit-Sample-App"
    })
    @GET("com.example.topsy.mytable/{stuff}")
        //here is the other url part.best way is to start using /
    Call<StuffModel> getById(@Path("stuff") int stuff);


    @Headers({
            "Accept: application/json",
    })
    @GET("com.example.topsy.mytable")
        //here is the other url part.best way is to start using /
    Call<List<StuffModel>> getAll();


    @Headers({
            "Accept: application/json",
//            "User-Agent: Retrofit-Sample-App"
    })
    @POST("com.example.topsy.mytable")
    Call<StuffModel> post(@Body StuffModel stuff);


    @Headers({
            "Content-Type: application/json",
//            "User-Agent: Retrofit-Sample-App"
    })
    @PUT("com.example.topsy.mytable/{stuff}")
    Call<StuffModel> update(@Path("stuff") int stuff, @Body StuffModel stuffModel);
}
