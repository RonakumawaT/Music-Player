package com.example.tablayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class favoriteAdapter extends RecyclerView.Adapter<favoriteAdapter.viewHolder> {

    ArrayList<favoriteSongModel> favoriteSongModels=new ArrayList<>();
    Context context;


    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.sample_favorite_row, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {

        favoriteSongModel favoriteSongModel = favoriteSongModels.get(position);
        holder.textView.setText(favoriteSongModel.getFavoriteSongName());
    }

    @Override
    public int getItemCount() {
        return favoriteSongModels.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {

        TextView textView;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.TVFav);
        }
    }
}
