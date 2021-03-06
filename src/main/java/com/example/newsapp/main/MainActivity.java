package com.example.newsapp.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.detail.DetailInfo;
import com.example.newsapp.entity.Article;
import com.example.newsapp.entity.Example;
import com.example.newsapp.network.RetrofitBuilder;
import com.example.newsapp.recycler.MainAdapter;
import com.example.newsapp.recycler.OnItemClickListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.SimpleDateFormat;
import java.util.Date;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements OnItemClickListener {
    MainAdapter adapter;
    RecyclerView recyclerView;

    public static String LANGUAGE = "ru";

    private Button ruBtn;
    private Button usBtn;
    private Button chBtn;
    private Button jpBtn;
    private Button deBtn;
    private Button skrBtn;
    public AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        itemViews();
        ConRes();
        Retro();
        langChange();

        MobileAds.initialize(this, initializationStatus -> {


        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        Log.d("ololo", "ad");
//        AdView adView = new AdView(this);
//        adView.setAdSize(AdSize.BANNER);
//        adView.setAdUnitId("ca-app-pub-4708068541671420/6340454903");


    }

//    public String convert(long dt) {
//        Date date = new Date(dt * 1000L);
//        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
//        String formatted = sdf.format(date);
//        return formatted;
//    }
//
//    public static String convert(String sunrise){
//        return sunrise;
//    }


    public void itemViews() {
        recyclerView = findViewById(R.id.recycle_view);

        ruBtn = findViewById(R.id.ruLang);
        usBtn = findViewById(R.id.usLang);
        chBtn = findViewById(R.id.chLang);
        jpBtn = findViewById(R.id.jpLang);
        deBtn = findViewById(R.id.deLang);
        skrBtn = findViewById(R.id.skrLang);
    }

    public void langChange() {
        ruBtn.setOnClickListener(view -> {
            LANGUAGE = "ru";
            Retro();
        });
        usBtn.setOnClickListener(view -> {
            LANGUAGE = "us";
            Retro();
        });
        chBtn.setOnClickListener(view -> {
            LANGUAGE = "cn";
            Retro();
        });
        jpBtn.setOnClickListener(view -> {
            LANGUAGE = "jp";
            Retro();
        });
        deBtn.setOnClickListener(view -> {
            LANGUAGE = "de";
            Retro();
        });
        skrBtn.setOnClickListener(view -> {
            LANGUAGE = "kr";
            Retro();
        });


    }

    public void Retro() {
        Log.d("olololo", "ololo");
        RetrofitBuilder.getInstance().getInfo(LANGUAGE, "e0670b0013b245e29427f7f2b9902407")
                .enqueue(new Callback<Example>() {
                    @Override
                    public void onResponse(Call<Example> call, Response<Example> response) {
                        if (response.body() != null) {
                            displayResult(response.body());
                            Log.e("TAG", "onResponse: " + response);
                        }
                    }

                    @Override
                    public void onFailure(Call<Example> call, Throwable t) {
                        Toast.makeText(MainActivity.this, "An error occurred during networking",
                                Toast.LENGTH_SHORT).show();
                    }
                });
    }

    public void ConRes() {
        adapter = new MainAdapter(this);
        recyclerView.setAdapter(adapter);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(manager);

    }

    @Override
    public void onItemClick(Article article) {
        Intent intent = new Intent(this, DetailInfo.class);
        intent.putExtra("description", article.getDescription());
        intent.putExtra("image", article.getUrlToImage());
        intent.putExtra("title", article.getTitle());
        intent.putExtra("url", article.getUrl());
        intent.putExtra("publishedAt", article.getPublishedAt());
        intent.putExtra("language", LANGUAGE);
        startActivity(intent);
    }

    public void displayResult(Example body) {
        adapter.update(body.getArticles());
    }
}

