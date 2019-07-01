package com.hussain.fixturewc18.model;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

import static com.hussain.fixturewc18.constant.constant.MatchFixtureURl;

public class Api {
    public static Retrofit retrofit = null;

    public static Retrofit getAllMatchFixtureData(){
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(MatchFixtureURl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

    public interface ApiInterface{
        @FormUrlEncoded
        @POST("read.php")
        Call<MatchFixture> all(@Field("language") String language);
        //Call<List<Record>> records();
    }
}
