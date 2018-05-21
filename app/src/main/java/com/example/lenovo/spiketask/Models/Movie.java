package com.example.lenovo.spiketask.Models;

/**
 * Created by Lenovo on 18/05/2018.
 * Romance,
 Comedy,Action,Thriller,Drama,Animation;
 */

public class Movie  {
    private String Name;
    private MovieGenre genre;
    private String FavActor;
    private String id;

public Movie(){}
    public Movie(String name,String factor,MovieGenre g){
        Name=name;
        genre=g;
        FavActor=factor;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Enum getGenre() {
        return genre;
    }

    public void setGenre(MovieGenre g) {

        genre=g;

    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFavActor() {
        return FavActor;
    }

    public void setFavActor(String favActor) {
        FavActor = favActor;
    }
}
