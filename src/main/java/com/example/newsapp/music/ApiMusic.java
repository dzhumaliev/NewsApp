package com.example.newsapp.music;


import retrofit2.Call;
import retrofit2.http.GET;

interface ApiMusic {
    @GET("studio")
    Call<MainModel> getMusic();


}
