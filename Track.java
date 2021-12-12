package com.example.tablayout;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.os.Environment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Track extends Fragment implements RvItemClickListener {

    public Track() {
        // Required empty public constructor
    }

    RecyclerView recyclerView;
    TrackAdapter TrackAdapter;
    ArrayList<String> items;
    ArrayList<File> mySongs;
    ImageView search;

    private favoriteSongViewModel songViewModel;

//    ArrayList<songList> songLists;
//    public static favoriteDatabase favoriteDatabase;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.trackfragment, container, false);

        search = view.findViewById(R.id.IVSearch);


        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));


        runTimePermission();



        return view;
    }

    public void runTimePermission() {

        Dexter.withActivity(getActivity())
                .withPermission(Manifest.permission.READ_EXTERNAL_STORAGE)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                        mySongs = fetchSongs(Environment.getExternalStorageDirectory());

                        items = new ArrayList<>(mySongs.size());
                        for (int i = 0; i < mySongs.size(); i++) {
                            items.add(mySongs.get(i).getName().replace(".mp3", " "));
                        }
                        TrackAdapter = new TrackAdapter(items, getContext(), Track.this::onItemClick);
                        recyclerView.setAdapter(TrackAdapter);
                    }

                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                        permissionToken.continuePermissionRequest();

                    }
                }).check();
    }

    public ArrayList<File> fetchSongs(File file) {
        ArrayList arrayList = new ArrayList();
        File[] songs = file.listFiles();
        if (songs != null) {
            for (File myFile : songs) {
                if (!myFile.isHidden() && myFile.isDirectory()) {
                    arrayList.addAll(fetchSongs(myFile));
                } else {
                    if (myFile.getName().endsWith(".mp3") && !myFile.getName().startsWith(".")) {
                        arrayList.add(myFile);
                    }
                }
            }
        }
        return arrayList;
    }

    @Override
    public void onItemClick(int position) {
        String current = items.get(position);
        Intent intent = new Intent(getContext(), playSong.class);
        intent.putExtra("currentSong", current);
        intent.putExtra("allSongs", mySongs);
        intent.putExtra("position", position);
        startActivity(intent);
    }
}