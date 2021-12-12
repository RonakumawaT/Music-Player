package com.example.tablayout;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

public class fav extends Fragment {

    public fav() {
        // Required empty public constructor
    }

    private favoriteSongViewModel songViewModel;
    favoriteAdapter adapter;
    RecyclerView recyclerView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fav_fragment, container, false);

        recyclerView=view.findViewById(R.id.favRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        adapter=new favoriteAdapter();

        songViewModel=new ViewModelProvider(this).get(favoriteSongViewModel.class);
        songViewModel.getAllFavoriteSongs().observe(this, new Observer<List<favoriteSongModel>>() {
            @Override
            public void onChanged(List<favoriteSongModel> favoriteSongModels) {



            }
        });



        return view;
    }
}