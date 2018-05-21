package com.example.lenovo.spiketask.Models;

/**
 * Created by Lenovo on 18/05/2018.
 */

public class Series {



        private String Name;
        private MovieGenre genre;
        private String FavActor;
private String id;
public Series(){}
        public Series(String name,MovieGenre g,String factor){
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

        public void setGenre(MovieGenre genre) {
            this.genre = genre;
        }

        public String getFavActor() {
            return FavActor;
        }

        public void setFavActor(String favActor) {
            FavActor = favActor;
        }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
