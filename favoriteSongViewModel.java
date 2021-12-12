package com.example.tablayout;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class favoriteSongViewModel extends AndroidViewModel {

    private favoriteSongRepository favoriteSongRepository;

    private final LiveData<List<favoriteSongModel>> allFavoriteSongs;

    public favoriteSongViewModel(@NonNull Application application) {
        super(application);

        favoriteSongRepository=new favoriteSongRepository(application);
        allFavoriteSongs=favoriteSongRepository.getAllFavoriteSongs();
    }

    LiveData<List<favoriteSongModel>> getAllFavoriteSongs(){
        return allFavoriteSongs;
    }

    public void insert(favoriteSongModel favoriteSongModel){
        favoriteSongRepository.insert(favoriteSongModel);
    }
}
