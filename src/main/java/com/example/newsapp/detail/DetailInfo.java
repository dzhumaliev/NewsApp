package com.example.newsapp.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.webView.WebViewActivity;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

public class DetailInfo extends AppCompatActivity {

    ImageView imageView;
    TextView description;
    TextView title;
    TextView urlClick;
    TextView publishedAt;
    String mainUrl;
    String lang;
    Button backBtn;
    Button shareBtn;
    public AdView mAdView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        initViews();
        getIntentData();
        changeLangBtn();

        urlClick.setOnClickListener(view -> {

            Intent intent = new Intent(getApplicationContext(), WebViewActivity.class);
            intent.putExtra("urlView", mainUrl);
            startActivity(intent);
        });

        backBtn.setOnClickListener(view -> finish());
        shareBtn.setOnClickListener(view -> {
            Intent intent = new Intent();
            intent.putExtra(Intent.EXTRA_TEXT, mainUrl);
            intent.setAction(Intent.ACTION_SEND);
            intent.setType("text/*");
            if (intent.resolveActivity(getPackageManager()) != null) {
                startActivity(intent);
            }
        });


        MobileAds.initialize(this, initializationStatus -> {


        });

        mAdView = findViewById(R.id.adView);
        AdRequest adRequest = new AdRequest.Builder().addTestDevice("TEST_DEVICE_ID").build();
        mAdView.loadAd(adRequest);

        Log.d("ololo", "ad");
        AdView adView = new AdView(this);
        adView.setAdSize(AdSize.BANNER);
        adView.setAdUnitId("ca-app-pub-4708068541671420/6340454903");
    }

    public void initViews() {
        imageView = findViewById(R.id.imageDet);
        description = findViewById(R.id.textDesc);
        urlClick = findViewById(R.id.url);
        title = findViewById(R.id.textTitle);
        publishedAt = findViewById(R.id.publishedTime);
        backBtn = findViewById(R.id.back);
        shareBtn = findViewById(R.id.share);
    }

    @SuppressLint("SimpleDateFormat")
    private void getIntentData() {
        Intent intent = getIntent();
        mainUrl = intent.getStringExtra("url");
        lang = intent.getStringExtra("language");
        String mainDate = intent.getStringExtra("publishedAt");


        setText();
        dateConvert(mainDate);
    }

    @SuppressLint("SimpleDateFormat")
    public void dateConvert(String mainDate) {
        Date from = null;      /////convert date
        try {
            from = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    .parse(Objects.requireNonNull(mainDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String to = new SimpleDateFormat("dd-MM-yyyy HH:mm")
                .format(from);
        publishedAt.setText(to);

    }

    public void setText() {
        Intent intent = getIntent();
        description.setText(intent.getStringExtra("description"));
        title.setText(intent.getStringExtra("title"));
        Glide.with(getBaseContext()).load(intent.getStringExtra("image")).into(imageView);
    }

    @SuppressLint("SetTextI18n")
    public void changeLangBtn() {

        if (lang.equals("ru")) {
            urlClick.setText("Подробнее");
        }
        if (lang.equals("de")) {
            urlClick.setText("Weitere Details");
        }
        if (lang.equals("jp")) {
            urlClick.setText("詳細");
        }
        if (lang.equals("cn")) {
            urlClick.setText("更多细节");
        }
        if (lang.equals("us")) {
            urlClick.setText("More details");
        }
        if (lang.equals("kr")) {
            urlClick.setText("자세한 내용");
        }

    }
}
