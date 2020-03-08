package com.example.newsapp.music;


import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;


public class MusicRetrofitBuilder {
    public static ApiMusic instances;
    private static OkHttpClient clients;


    public static ApiMusic getInstance() {
        if (instances == null) {
            instances = buildRetrofits();

        }
        return instances;
    }

    private static ApiMusic buildRetrofits() {
        return new Retrofit.Builder()
                .baseUrl("http://starlord.hackerearth.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(getClients())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
                .create(ApiMusic.class);

    }

    private static OkHttpClient getClients() {
        if (clients == null) {
            clients = okhttpBuilders();
        }
        return clients;
    }

    private static OkHttpClient okhttpBuilders() {
        return new OkHttpClient.Builder()
                .connectTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
                .build();
    }
}
