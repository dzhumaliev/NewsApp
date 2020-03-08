package com.example.newsapp.music;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.music.recycler.MusicAdapter;
import com.example.newsapp.recycler.MainAdapter;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Music extends AppCompatActivity {

    MusicAdapter adapters;
    RecyclerView recyclerViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_music);

        recyclerViews = findViewById(R.id.recycle_view_music);
        adapters = new MusicAdapter();
        recyclerViews.setAdapter(adapters);
        RecyclerView.LayoutManager manager = new LinearLayoutManager(this);
        recyclerViews.setLayoutManager(manager);
        RetroMus();

    }


    public void RetroMus() {
        Log.d("olololo", "musicResp");
        MusicRetrofitBuilder.getInstance().getMusic()
                .enqueue(new Callback<MainModel>() {
                    @Override
                    public void onResponse(Call<MainModel> call, Response<MainModel> response) {
                        if (response.body() != null) {
                            displayMusicResult(response.body());
                            Log.e("TAG", "onResponse: " + response);
                        }
                    }

                    @Override
                    public void onFailure(Call<MainModel> call, Throwable t) {
                        Toast.makeText(Music.this, "An error occurred during networking",
                                Toast.LENGTH_SHORT).show();
                        Log.d("error", call.request().toString());
                    }
                });
    }

    public void displayMusicResult(MainModel modelArrayList) {
        adapters.updateMusic(modelArrayList.list);
    }

}