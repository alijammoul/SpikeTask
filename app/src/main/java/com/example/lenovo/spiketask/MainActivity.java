package com.example.lenovo.spiketask;

import android.content.Intent;
import android.graphics.Canvas;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.lenovo.spiketask.Models.Article;
import com.example.lenovo.spiketask.Models.Book;
import com.example.lenovo.spiketask.Models.BookGenre;
import com.example.lenovo.spiketask.Models.Movie;
import com.example.lenovo.spiketask.Models.MovieGenre;
import com.example.lenovo.spiketask.Models.Music;
import com.example.lenovo.spiketask.Models.Series;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private FirebaseAuth fba;
    private FirebaseUser user;
private Button btnmovie,btnbook,btnmusic,btnarticle,btnseries;
TextView v;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fba = FirebaseAuth.getInstance();

        user=fba.getCurrentUser();
        Toast.makeText(this,"Welcome " +user.getEmail(),Toast.LENGTH_SHORT).show();

        btnmovie=(Button)findViewById(R.id.Moviebtn);
        btnbook=(Button)findViewById(R.id.Bookbtn);
        btnmusic=(Button)findViewById(R.id.Musicbtn);
        btnarticle=(Button)findViewById(R.id.Articlebtn);
        btnseries=(Button)findViewById(R.id.Seriesbtn);

        btnmovie.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent m = new Intent(MainActivity.this,HomeActivity.class);
        m.putExtra("Model","Movie");
        startActivity(m);

    }
});

        btnbook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(MainActivity.this,HomeActivity.class);
               m.putExtra("Model","Book");
               startActivity(m);
            }
        });



        btnmusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(MainActivity.this,HomeActivity.class);
                m.putExtra("Model","Music");
                startActivity(m);
            }
        });
        btnarticle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(MainActivity.this,HomeActivity.class);
                m.putExtra("Model","Article");
                startActivity(m);
            }
        });
        btnseries.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent m = new Intent(MainActivity.this,HomeActivity.class);
                m.putExtra("Model","Series");
                startActivity(m);
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        v=(TextView)findViewById(R.id.textView) ;
        //v.setText(user.getEmail());
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }







    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_bookmark) {
            Intent m = new Intent(MainActivity.this,CollectionActivity.class);
            m.putExtra("Mode","My Saved Collection");
            startActivity(m);
        } else if (id == R.id.nav_later) {
            Intent m = new Intent(MainActivity.this,CollectionActivity.class);
            m.putExtra("Mode","View Later");
            startActivity(m);
        }  else if (id == R.id.nav_logout) {
            fba.signOut();
            finish();
            Intent myIntent = new Intent(MainActivity.this, LoginActivity.class);
            startActivity(myIntent);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }



}
