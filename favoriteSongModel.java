package com.example.tablayout;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favoriteSongTable")
public class favoriteSongModel {

    @PrimaryKey
    @NonNull
    @ColumnInfo(name = "favoriteSongTable")
    private String favoriteSongName;

    public favoriteSongModel(@NonNull String favoriteSongName) {
        this.favoriteSongName = favoriteSongName;
    }

    public String getFavoriteSongName() {
        return this.favoriteSongName;
    }

}
