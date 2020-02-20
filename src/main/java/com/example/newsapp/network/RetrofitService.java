package com.example.newsapp.network;


import com.example.newsapp.entity.Example;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RetrofitService {

//    @GET(ApiEndPoint.EVERYTHING)
//    Call<Example> getNews(@Query("q") String type,
//                          @Query("from") String date,
//                          @Query("sortBy") String sortBy,
//                          @Query("apiKey") String appKey,
//                          @Query("page") String page);

    @GET("v2/top-headlines")
    Call<Example> getInfo(@Query("country") String country,
                          @Query("apiKey") String apiKey
    );
}
