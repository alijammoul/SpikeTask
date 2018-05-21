package com.example.lenovo.spiketask.Models;

/**
 * Created by Lenovo on 18/05/2018.
 */

public enum BookGenre {

    Fiction,Novel,ScienceFiction,Romance,Mystery,Fantasy;

    public static BookGenre map(String v){
        switch(v){
            case "Fiction": return BookGenre.Fiction;
            case "ScienceFiction": return BookGenre.ScienceFiction;
            case "Romance": return BookGenre.Romance;
            case "Mystery": return BookGenre.Mystery;
            case "Fantasy": return BookGenre.Fantasy;
            case "Novel": return BookGenre.Novel;
        }
        return null;
    }
}
