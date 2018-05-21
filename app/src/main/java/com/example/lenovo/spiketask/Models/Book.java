package com.example.lenovo.spiketask.Models;

/**
 * Created by Lenovo on 18/05/2018.
 */

public class Book  {
    private String Name;
    private String Author;
    private BookGenre genre;
    private String id;
public Book(){}
    public Book(String Name,String Author,BookGenre genre){
        this.Name=Name;
        this.Author=Author;
        this.genre=genre;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getId() {
        return id;
    }

    public Enum getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public void setId(String id) {
        this.id = id;
    }
}
