package com.example.newsapp.webView;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.newsapp.R;

public class WebViewActivity extends AppCompatActivity {

    WebView webViewUrl;
    Button back;

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web_view);

        back = findViewById(R.id.backBtn);
        webViewUrl = findViewById(R.id.webView);

        Intent intent = getIntent();
        String url = intent.getStringExtra("urlView");


        webViewUrl.getSettings().setJavaScriptEnabled(true);

        webViewUrl.getSettings().setBuiltInZoomControls(true);
        final Activity activity = this;
        webViewUrl.setWebViewClient(new WebViewClient() {
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                Toast.makeText(activity, description, Toast.LENGTH_SHORT).show();
            }
        });
        webViewUrl.loadUrl(url);

        back.setOnClickListener(v -> {
            finish();
        });
    }

}
