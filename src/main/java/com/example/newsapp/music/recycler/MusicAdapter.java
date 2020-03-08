package com.example.newsapp.music.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.music.MusicModel;

import java.util.ArrayList;
import java.util.List;


public class MusicAdapter extends RecyclerView.Adapter<MusicViewHolder> {

    private List<MusicModel> model = new ArrayList<>();


    @NonNull
    @Override
    public MusicViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView =LayoutInflater.from(parent.getContext()).inflate(R.layout.music_item, parent, false);
        return new MusicViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MusicViewHolder holder, int position) {
        holder.bind(model.get(position));

    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public void updateMusic(List<MusicModel> modelList) {
        this.model = modelList;
        notifyDataSetChanged();

    }
}
