package com.example.newsapp.music.recycler;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapp.R;
import com.example.newsapp.music.MusicModel;
import com.squareup.picasso.Picasso;

class MusicViewHolder extends RecyclerView.ViewHolder {

    private TextView textView;
    private ImageView imageView;

    MusicViewHolder(@NonNull View itemView) {
        super(itemView);

        textView = itemView.findViewById(R.id.desc);
        imageView = itemView.findViewById(R.id.imageMus);

    }

    void bind(MusicModel model) {
        textView.setText(model.getSong());
        Picasso.get().load(model.getCover_image()
                + ".png").into(imageView);
    }
}