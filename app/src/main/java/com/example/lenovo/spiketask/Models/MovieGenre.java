package com.example.lenovo.spiketask.Models;

/**
 * Created by Lenovo on 18/05/2018.
 */

public enum MovieGenre {

    Romance,
    Comedy,Action,Thriller,Drama,Animation;




    public static MovieGenre map(String v){
        if(v==null){
            return null;
        }
        switch(v){
            case "Romance": return MovieGenre.Romance;
            case "Comedy": return MovieGenre.Comedy;
            case "Action": return MovieGenre.Action;
            case "Drama": return MovieGenre.Drama;
            case "Animation": return MovieGenre.Animation;
            case "Thriller": return MovieGenre.Thriller;
        }
        return null;
    }
}
