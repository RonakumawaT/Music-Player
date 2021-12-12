package com.example.tablayout;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {favoriteSongModel.class}, version = 1, exportSchema = false)
public abstract class favoriteSongRoomDatabase extends RoomDatabase {

    public abstract favoriteSongDao favoriteSongDao();

    private static volatile favoriteSongRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static favoriteSongRoomDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (favoriteSongModel.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            favoriteSongRoomDatabase.class, "favoriteSong_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
