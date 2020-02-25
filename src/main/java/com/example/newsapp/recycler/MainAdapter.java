package com.example.newsapp.recycler;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapp.R;
import com.example.newsapp.entity.Article;

import java.util.ArrayList;
import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainViewHolder> {

    private List<Article> articles = new ArrayList<>();
    private OnItemClickListener listener;

    public MainAdapter(OnItemClickListener listener) {
        this.listener = listener;
    }


    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.view_holder, parent, false);
        return new MainViewHolder(view);



    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.onBind(articles.get(position), listener);
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void update(List<Article> articles) {
        this.articles = articles;
        notifyDataSetChanged();

    }

}

class MainViewHolder extends RecyclerView.ViewHolder {

    private TextView textOne;
    private ImageView imageView;

    public MainViewHolder(@NonNull View itemView) {
        super(itemView);

        textOne = itemView.findViewById(R.id.textTitle);
        imageView = itemView.findViewById(R.id.imageOne);
    }

    @SuppressLint("CheckResult")
    public void onBind(Article article, OnItemClickListener listener) {
        textOne.setText(String.valueOf(article.getTitle()));
        Glide.with(itemView.getContext()).load(String.valueOf(article.getUrlToImage())).into(imageView);

        itemView.setOnClickListener(view ->
                listener.onItemClick(article));

//        itemView.setOnClickListener(view ->
//                Log.d("ololo", "inAdapter"));





    }



}