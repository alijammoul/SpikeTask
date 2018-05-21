package com.example.lenovo.spiketask.Models;

/**
 * Created by Lenovo on 18/05/2018.
 */

public enum MusicGenre {

    Pop,Jazz,Blues,Rock,Metal,RandB;



    public static MusicGenre map(String v){
        switch(v){
            case "Pop": return MusicGenre.Pop;
            case "Jazz": return MusicGenre.Jazz;
            case "Blues": return MusicGenre.Blues;
            case "Rock": return MusicGenre.Rock;
            case "RandB": return MusicGenre.RandB;
            case "Metal": return MusicGenre.Metal;
        }
        return null;
    }
}
