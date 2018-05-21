package com.example.lenovo.spiketask.Models;

/**
 * Created by Lenovo on 18/05/2018.
 */

public class Music {

    private String Name;
    private String Album;
    private String Artist;
    private MusicGenre genre;
    private String id;
public Music(){}
    public Music(String Name,String Album,String Artist,MusicGenre genre){
        this.Album=Album;
        this.Artist=Artist;
        this.genre=genre;
        this.Name=Name;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAlbum() {
        return Album;
    }

    public void setAlbum(String album) {
        Album = album;
    }

    public String getArtist() {
        return Artist;
    }

    public void setArtist(String artist) {
        Artist = artist;
    }

    public MusicGenre getGenre() {
        return genre;
    }

    public void setGenre(MusicGenre genre) {
        this.genre = genre;
    }

    public void setId(String id) {
        this.id = id;
    }
}
