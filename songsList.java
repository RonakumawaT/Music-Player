package com.example.tablayout;

public class songsList {

    private String songName;
    private int id;

    public songsList(String songName, int id) {
        this.songName = songName;
        this.id = id;
    }

    public songsList() {
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
