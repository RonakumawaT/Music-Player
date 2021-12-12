package com.example.tablayout;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.provider.ContactsContract;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

public class artists extends Fragment {

    final Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
    ArrayList<artistModel> artists;

    public artists() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_artists, container, false);

        getArtists();

        return view;
    }

    public void getArtists() {
        Cursor cursor = getActivity().getApplicationContext().getContentResolver().query(uri, null, null, null, null);
        while (cursor.moveToNext()) {

            final String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST));
            artists = new ArrayList<>();
            artists.add(new artistModel(artist));
        }
    }
//
//    final Uri uri = MediaStore.Audio.Albums.EXTERNAL_CONTENT_URI;
//
//    final Cursor cursor = getContentResolver().query(uri, null, null, null, null);
//        while (cursor.moveToNext()) {
//        final String artist = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ARTIST));
//        final String album = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM));
//        final String albumart = cursor.getString(cursor.getColumnIndexOrThrow(MediaStore.Audio.Albums.ALBUM_ART));
//        arrayList.add(artist);
}
