package com.example.lenovo.spiketask.Models;

/**
 * Created by Lenovo on 18/05/2018.
 */

public class Article {

    private String Name;
    private String Author;
    private String Source;
    private String PublishedDate;
    private BookGenre genre;
    private String id;

public Article(){}
    public Article(String Name, String Author,BookGenre genre, String Source, String publishedDate ) {
        this.Name=Name;
        this.Author=Author;
        this.genre=genre;
        this.Source = Source;
        PublishedDate = publishedDate;

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

    public String getSource() {
        return Source;
    }

    public void setSource(String source) {
        Source = source;
    }

    public String getPublishedDate() {
        return PublishedDate;
    }

    public void setPublishedDate(String publishedDate) {
        PublishedDate = publishedDate;
    }

    public BookGenre getGenre() {
        return genre;
    }

    public void setGenre(BookGenre genre) {
        this.genre = genre;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
