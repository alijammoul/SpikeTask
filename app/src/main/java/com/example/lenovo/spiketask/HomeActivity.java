package com.example.lenovo.spiketask;

import android.content.Intent;
import android.graphics.Canvas;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.widget.Toast;

import com.example.lenovo.spiketask.Models.Article;
import com.example.lenovo.spiketask.Models.Book;
import com.example.lenovo.spiketask.Models.BookGenre;
import com.example.lenovo.spiketask.Models.Movie;
import com.example.lenovo.spiketask.Models.MovieGenre;
import com.example.lenovo.spiketask.Models.Music;
import com.example.lenovo.spiketask.Models.Series;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HomeActivity extends AppCompatActivity {
    private List<Movie> movieList = new ArrayList<>();
    private List<Book> bookList = new ArrayList<>();
    private List<Series> seriesList = new ArrayList<>();
    private List<Article> articleList = new ArrayList<>();
    private List<Music> musicList = new ArrayList<>();
    private RecyclerView recyclerView;
    private MAdapter mAdapter;
    private String type;
    private FirebaseFirestore fm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        fm=FirebaseFirestore.getInstance();
        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        mAdapter = new MAdapter(movieList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);check();



    }
    public void check(){
    Intent intent = getIntent();
    type =getIntent().getStringExtra("Model").toString();

    switch(type){
        case "Movie":
            prepareMovieData();
            break;
        case "Book":prepareBookData();
            break;
        case "Music": prepareMusicData();
            break;
        case "Article":prepareArticleData();
            break;
        case "Series":prepareSeriesData();
            break;
    }
}

    private void prepareMovieData() {

        Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", MovieGenre.Action);
        movieList.add(movie);

        movie = new Movie("Inside Out", "Animation, Kids & Family", MovieGenre.Action);
        movieList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", MovieGenre.Romance);
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", MovieGenre.Romance);
        movieList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", MovieGenre.Action);
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", MovieGenre.Drama);
        movieList.add(movie);

        movie = new Movie("Up", "Animation", MovieGenre.Comedy);
        movieList.add(movie);



        movie = new Movie("Inside Out", "Animation, Kids & Family", MovieGenre.Action);
        movieList.add(movie);

        movie = new Movie("Star Wars: Episode VII - The Force Awakens", "Action", MovieGenre.Romance);
        movieList.add(movie);

        movie = new Movie("Shaun the Sheep", "Animation", MovieGenre.Romance);
        movieList.add(movie);

        movie = new Movie("The Martian", "Science Fiction & Fantasy", MovieGenre.Action);
        movieList.add(movie);

        movie = new Movie("Mission: Impossible Rogue Nation", "Action", MovieGenre.Drama);
        movieList.add(movie);

        movie = new Movie("Up", "Animation", MovieGenre.Comedy);
        movieList.add(movie);
        mAdapter.notifyDataSetChanged();
    }
    private void prepareArticleData() {
        //Adapter(articleList);
        //String Name, String Author, String Source, String publishedDate, BookGenre genre
        Article a = new Article("java","saeed","wwwlol.com","8/3/2018", BookGenre.Fantasy);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        articleList.add(a);
        //mAdapter.notifyDataSetChanged();

    }
    private void prepareBookData(){

    }
    private void prepareSeriesData(){}
    private void prepareMusicData(){}



}
