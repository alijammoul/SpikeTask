package com.example.lenovo.spiketask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.lenovo.spiketask.Models.Article;
import com.example.lenovo.spiketask.Models.Book;
import com.example.lenovo.spiketask.Models.BookGenre;
import com.example.lenovo.spiketask.Models.Movie;
import com.example.lenovo.spiketask.Models.MovieGenre;
import com.example.lenovo.spiketask.Models.Music;
import com.example.lenovo.spiketask.Models.MusicGenre;
import com.example.lenovo.spiketask.Models.Series;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
    private final String movieurl="https://api.myjson.com/bins/9z2uq",bookurl="https://api.myjson.com/bins/170un6",musicurl="https://api.myjson.com/bins/1dowma",seriesurl="https://api.myjson.com/bins/1evrtu",articleurl="https://api.myjson.com/bins/9qpxe";

    private FirebaseFirestore fs;
    private FirebaseAuth fba;
    private FirebaseUser user;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

        fba=FirebaseAuth.getInstance();
        user=fba.getCurrentUser();
        fs=FirebaseFirestore.getInstance();


        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        check();//check what model to retrieve
      ;

        recyclerView.addOnItemTouchListener(
                new RecyclerItemClickListener(getApplicationContext(), recyclerView ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view,final  int position) {
                        // save

                        switch (type){
                            case "Movie": Map<String,Object> map = new HashMap<>();
                                map.put("name",movieList.get(position).getName());
                                map.put("favActor",movieList.get(position).getName());
                                map.put("genre",movieList.get(position).getGenre().toString());


                                fs.collection("Users").document(user.getEmail())
                                        .collection(type).add(map)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                 movieList.get(position).setId(documentReference.getId());//new line
                                                Toast.makeText(getApplicationContext(),
                                                        "Event document has been added",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Toast.makeText(getApplicationContext(),
                                                        "Event document could not be added",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });

                                break;
                            case "Music":Map<String,Object> map1 = new HashMap<>();
                                map1.put("name",musicList.get(position).getName());
                                map1.put("album",musicList.get(position).getAlbum());
                                map1.put("artist",musicList.get(position).getArtist());
                                map1.put("genre",musicList.get(position).getGenre().toString());
                                fs.collection("Users").document(user.getEmail())
                                        .collection(type).add(map1)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                musicList.get(position).setId(documentReference.getId());//new line
                                                Toast.makeText(getApplicationContext(),
                                                        "Event document has been added",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Toast.makeText(getApplicationContext(),
                                                        "Event document could not be added",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });break;
                            case "Book":Map<String,Object> map2 = new HashMap<>();
                                map2.put("name",bookList.get(position).getName());
                                map2.put("author",bookList.get(position).getAuthor());
                                map2.put("genre",bookList.get(position).getGenre().toString());

                                fs.collection("Users").document(user.getEmail())
                                        .collection(type).add(map2)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                bookList.get(position).setId(documentReference.getId());//new line
                                                Toast.makeText(getApplicationContext(),
                                                        "Event document has been added",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Toast.makeText(getApplicationContext(),
                                                        "Event document could not be added",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });break;
                            case "Series":Map<String,Object> map3 = new HashMap<>();
                                map3.put("name",seriesList.get(position).getName());
                                map3.put("genre",seriesList.get(position).getGenre().toString());
                                map3.put("actor",seriesList.get(position).getFavActor());
                                fs.collection("Users").document(user.getEmail())
                                        .collection(type).add(map3)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                seriesList.get(position).setId(documentReference.getId());//new line
                                                Toast.makeText(getApplicationContext(),
                                                        "Event document has been added",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Toast.makeText(getApplicationContext(),
                                                        "Event document could not be added",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });break;
                            case "Article":
                                Map<String,Object> map4 = new HashMap<>();
                                map4.put("name",articleList.get(position).getName());
                                map4.put("genre",articleList.get(position).getGenre().toString());
                                map4.put("author",articleList.get(position).getAuthor());
                                map4.put("date",articleList.get(position).getPublishedDate());
                                map4.put("source",articleList.get(position).getSource());
                                fs.collection("Users").document(user.getEmail())
                                        .collection(type).add(map4)
                                        .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                            @Override
                                            public void onSuccess(DocumentReference documentReference) {
                                                articleList.get(position).setId(documentReference.getId());//new line
                                                Toast.makeText(getApplicationContext(),
                                                        "Event document has been added",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {

                                                Toast.makeText(getApplicationContext(),
                                                        "Event document could not be added",
                                                        Toast.LENGTH_SHORT).show();
                                            }
                                        });break;

                        }



                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // later

                    }
                })
        );

    }
    public void initm(List a,List b,List c, List d,List e,int n){
        mAdapter = new MAdapter(a,b,c,d,e,n);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);
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
        initm(movieList,bookList,seriesList,articleList,musicList,0);
       final ProgressDialog d = new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);

        d.setMessage("working...");
        d.show();

        JsonArrayRequest j = new JsonArrayRequest(movieurl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject o = response.getJSONObject(i);
                        Movie movie = new Movie();
                        movie.setName(o.getString("Name"));
                        movie.setFavActor(o.getString("Actor"));
                        movie.setGenre(MovieGenre.map(o.getString("Genre")));
                        //Romance,Comedy,Action,Thriller,Drama,Animation


                        movieList.add(movie);
                    }catch (JSONException e){
                        e.printStackTrace();
                        d.dismiss();
                    }
                }
                mAdapter.notifyDataSetChanged();
                d.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley","Error");
                d.dismiss();
            }
        });
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(j);
       // Movie movie = new Movie("Mad Max: Fury Road", "Action & Adventure", MovieGenre.Action);
        //mAdapter.notifyDataSetChanged();
    }
    private void prepareArticleData() {
        initm(movieList,bookList,seriesList,articleList,musicList,1);
        final ProgressDialog d = new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);
        d.setMessage("working...");
        d.show();
        JsonArrayRequest j = new JsonArrayRequest(articleurl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject o = response.getJSONObject(i);
                        Article b = new Article();
                        b.setName(o.getString("Name"));
                        b.setAuthor(o.getString("Author"));
                        b.setGenre(BookGenre.map(o.getString("Genre")));
                        b.setPublishedDate(o.getString("PublishedDate"));
                        b.setSource(o.getString("Source"));
                        //Romance,Comedy,Action,Thriller,Drama,Animation


                        articleList.add(b);
                    }catch (JSONException e){
                        e.printStackTrace();
                        d.dismiss();
                    }
                }
                mAdapter.notifyDataSetChanged();
                d.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley","Error");
                d.dismiss();
            }
        });
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(j);

    }
    private void prepareBookData(){
        initm(movieList,bookList,seriesList,articleList,musicList,2);
        final ProgressDialog d = new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);
        d.setMessage("working...");
        d.show();
        JsonArrayRequest j = new JsonArrayRequest(bookurl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject o = response.getJSONObject(i);
                        Book b = new Book();
                        b.setName(o.getString("Name"));
                        b.setAuthor(o.getString("Author"));
                        b.setGenre(BookGenre.map(o.getString("Genre")));
                        //Romance,Comedy,Action,Thriller,Drama,Animation


                        bookList.add(b);
                    }catch (JSONException e){
                        e.printStackTrace();
                        d.dismiss();
                    }
                }
                mAdapter.notifyDataSetChanged();
                d.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley","Error");
                d.dismiss();
            }
        });
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(j);
        //String Name, String Author, String Source, String publishedDate, BookGenre genre

      //  mAdapter.notifyDataSetChanged();



    }
    private void prepareSeriesData(){
        initm(movieList,bookList,seriesList,articleList,musicList,4);
        final ProgressDialog d = new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);
        d.setMessage("working...");
        d.show();
        JsonArrayRequest j = new JsonArrayRequest(seriesurl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject o = response.getJSONObject(i);
                        Series b = new Series();
                        b.setName(o.getString("Name"));
                        b.setFavActor(o.getString("Actor"));
                        b.setGenre(MovieGenre.map(o.getString("Genre")));
                        //Romance,Comedy,Action,Thriller,Drama,Animation


                        seriesList.add(b);
                    }catch (JSONException e){
                        e.printStackTrace();
                        d.dismiss();
                    }
                }
                mAdapter.notifyDataSetChanged();
                d.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley","Error");
                d.dismiss();
            }
        });
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(j);
    }
    private void prepareMusicData(){
        initm(movieList,bookList,seriesList,articleList,musicList,3);
        final ProgressDialog d = new ProgressDialog(this,R.style.AppCompatAlertDialogStyle);
        d.setMessage("working...");
        d.show();
        JsonArrayRequest j = new JsonArrayRequest(musicurl, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for(int i=0;i<response.length();i++){
                    try{
                        JSONObject o = response.getJSONObject(i);
                        Music b = new Music();
                        b.setName(o.getString("Name"));
                        b.setArtist(o.getString("Artist"));
                        b.setGenre(MusicGenre.map(o.getString("Genre")));
                        b.setAlbum(o.getString("Album"));
                        //Romance,Comedy,Action,Thriller,Drama,Animation


                        musicList.add(b);
                    }catch (JSONException e){
                        e.printStackTrace();
                        d.dismiss();
                    }
                }
                mAdapter.notifyDataSetChanged();
                d.dismiss();
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e("Volley","Error");
                d.dismiss();
            }
        });
        RequestQueue q = Volley.newRequestQueue(this);
        q.add(j);
    }


}
