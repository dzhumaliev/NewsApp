package com.example.newsapp.detail;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DetailInfo extends AppCompatActivity {

    ImageView imageView;
    TextView description;
    TextView title;
    TextView urlClick;

    TextView publishedAt;

    String mainUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_info);
        imageView = findViewById(R.id.imageDet);
        description = findViewById(R.id.textDesc);
        urlClick = findViewById(R.id.url);
        title = findViewById(R.id.textTitle);
        publishedAt = findViewById(R.id.publishedTime);
        getIntentData();


        urlClick.setOnClickListener(view -> {
            Intent openUrlIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(mainUrl));
            startActivity(openUrlIntent);
        });
    }

    @SuppressLint("SimpleDateFormat")
    private void getIntentData() {
        Intent intent = getIntent();
        String titleText = intent.getStringExtra("title");
        String desc = intent.getStringExtra("description");
        String image = intent.getStringExtra("image");
        String mainDate = intent.getStringExtra("publishedAt");
        mainUrl = intent.getStringExtra("url");

        description.setText(desc);
        title.setText(titleText);
        Glide.with(getBaseContext()).load(image).into(imageView);


        Date from = null;      /////convert date
        try {
            from = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")
                    .parse(mainDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        String to = new SimpleDateFormat("dd-MM-yyyy HH:mm")
                .format(from);

        publishedAt.setText(to);

    }
}
