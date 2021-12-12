package com.example.tablayout;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface favoriteSongDao {

    // allowing the insert of the same word multiple times by passing a
    // conflict resolution strategy
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insert(favoriteSongModel favoriteSongName);

    @Query("DELETE FROM favoriteSongTable")
    void deleteAll();

    @Query("SELECT * FROM favoriteSongTable ORDER BY favoriteSongName ASC")
    LiveData<List<favoriteSongModel>> getFavoriteSong();
}
