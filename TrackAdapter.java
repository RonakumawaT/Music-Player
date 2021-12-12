package com.example.tablayout;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class TrackAdapter extends RecyclerView.Adapter<TrackAdapter.viewHolder> {

    ArrayList<String> list;
    Context context;
    private RvItemClickListener clickListener;

    public TrackAdapter(ArrayList<String> list, Context context, RvItemClickListener clickListener) {
        this.list = list;
        this.context = context;
        this.clickListener = clickListener;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_row_sample, parent, false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder holder, int position) {
        holder.text.setText(String.valueOf(list.get(position)));


    }

    @Override
    public int getItemCount() {
        return list.size();
    }


    public class viewHolder extends RecyclerView.ViewHolder {

        TextView text;
        ImageView fav;

        public viewHolder(@NonNull View itemView) {
            super(itemView);

            text = itemView.findViewById(R.id.textView);
            fav = itemView.findViewById(R.id.TVFav);

            text.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    clickListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}

