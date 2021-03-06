package com.example.lenovo.spiketask;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lenovo.spiketask.Models.Article;
import com.example.lenovo.spiketask.Models.Book;
import com.example.lenovo.spiketask.Models.Movie;
import com.example.lenovo.spiketask.Models.Music;
import com.example.lenovo.spiketask.Models.Series;
import com.mindorks.placeholderview.ViewHolder;

import java.util.List;



public class MAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<Movie> List;
    private List<Article> aList;
    private List<Book> booklist;
    private List<Music> musiclist;
    private List<Series> serieslist;
    private Context context;

    private int n;//used to detect what type of object is passed to adapter for viewholder matching

    public class ViewHolder0 extends RecyclerView.ViewHolder {//movie
        public TextView Name, Actor, genre;
        public RelativeLayout viewBackground;
        public CardView viewForeground;
        ImageView i;
        public ViewHolder0(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            genre = (TextView) view.findViewById(R.id.genre);
            Actor=(TextView) view.findViewById(R.id.AuthorArtist);
            i=(ImageView)view.findViewById(R.id.icon);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground=view.findViewById(R.id.card_view);

        }


    }
    public class ViewHolder1 extends RecyclerView.ViewHolder {//article
        public TextView Name, Author,Source,date, genre;
        public RelativeLayout viewBackground;
        public CardView viewForeground;
        ImageView i;
        public ViewHolder1(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            genre = (TextView) view.findViewById(R.id.genre);
            Author=(TextView) view.findViewById(R.id.AuthorArtist);
            Source=(TextView)view.findViewById(R.id.Source);
            date=(TextView)view.findViewById(R.id.Date);
            i=(ImageView)view.findViewById(R.id.icon);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground=view.findViewById(R.id.card_view);


        }


    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {//book
        public TextView Name, Author, genre;
        public RelativeLayout viewBackground;
        public CardView viewForeground;
        ImageView i;
        public ViewHolder2(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            genre = (TextView) view.findViewById(R.id.genre);
            Author=(TextView) view.findViewById(R.id.AuthorArtist);
            i=(ImageView)view.findViewById(R.id.icon);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground=view.findViewById(R.id.card_view);
        }


    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {//music
        public TextView Name, Artist,Album, genre;
        public RelativeLayout viewBackground;
        public CardView viewForeground;
        ImageView i;
        public ViewHolder3(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            genre = (TextView) view.findViewById(R.id.genre);
            Artist=(TextView) view.findViewById(R.id.AuthorArtist);
            Album=(TextView)view.findViewById(R.id.Source);
            i=(ImageView)view.findViewById(R.id.icon);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground=view.findViewById(R.id.card_view);
        }


    }
    public class ViewHolder4 extends RecyclerView.ViewHolder {//series
        public TextView Name, Actor, genre;
        public RelativeLayout viewBackground;
        public CardView viewForeground;
       public ImageView i;
        public ViewHolder4(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            genre = (TextView) view.findViewById(R.id.genre);
            Actor=(TextView) view.findViewById(R.id.AuthorArtist);
            i=(ImageView)view.findViewById(R.id.icon);
            viewBackground = view.findViewById(R.id.view_background);
            viewForeground=view.findViewById(R.id.card_view);
        }


    }
    @Override
    public int getItemViewType(int position) {

        return n;
    }
//movieList,bookList,seriesList,articleList,musicList,0
    //only use needed parameters
    public MAdapter(List<Movie> moviesList, List<Book> b, List<Series> s, List<Article> a, List<Music> m ,int n,Context c) {
        this.List = moviesList;
        booklist=b;
        serieslist=s;
        aList=a;
        musiclist=m;
        this.n=n;
        context=c;

    }


        @Override
        public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent,int viewType){
            View itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.model, parent, false);


                switch (viewType){
                    case 0: return new ViewHolder0(itemView);
                    case 1: return new ViewHolder1(itemView);
                    case 2: return new ViewHolder2(itemView);
                    case 3: return new ViewHolder3(itemView);
                    case 4: return new ViewHolder4(itemView);
                }
return null;
    }

        @Override
        public void onBindViewHolder (RecyclerView.ViewHolder holder,int position){
            Log.d("type",holder.getItemViewType()+"");
            switch (holder.getItemViewType()){
                case 0:
                    ViewHolder0 viewHolder0 = (ViewHolder0)holder;
                    Movie m = List.get(position);
                    viewHolder0.Name.setText("Movie Name :"+m.getName());
                    viewHolder0.genre.setText("Movie Genre :"+m.getGenre().toString());
                    viewHolder0.Actor.setText("Favorite Actor :"+m.getFavActor());
                    viewHolder0.i.setBackgroundResource(R.drawable.movie);
                   Glide.with(context)
                           .load(m.getName())
                           .into(viewHolder0.i);



                break;
                case 1:
                    ViewHolder1 viewHolder1 = (ViewHolder1)holder;
                    Article a = aList.get(position);

                    viewHolder1.Name.setText("Article Name :"+a.getName());
                    viewHolder1.genre.setText("Article Genre :"+a.getGenre().toString());//m.getGenre().toString()
                    viewHolder1.Author.setText("Article Author :"+a.getAuthor());
                    viewHolder1.Source.setText("Article Source :("+a.getSource()+")");
                    viewHolder1.date.setText("Date Published :"+a.getPublishedDate());
                    viewHolder1.i.setBackgroundResource(R.drawable.article);
                    Glide.with(context)
                            .load(a.getName())
                            .into(viewHolder1.i);

                break;
                case 2: ViewHolder2 viewHolder2=(ViewHolder2)holder;
                Book b = booklist.get(position);
                viewHolder2.Name.setText("Book Name :"+b.getName());
                viewHolder2.Author.setText("Book Author :"+b.getAuthor());
                viewHolder2.genre.setText("Book Genre :"+b.getGenre().toString());
                    viewHolder2.i.setBackgroundResource(R.drawable.book);
                    Glide.with(context)
                            .load(b.getName())
                            .into(viewHolder2.i);
                break;
                case 3:ViewHolder3 viewHolder3=(ViewHolder3)holder;
                    Music c = musiclist.get(position);
                    viewHolder3.Name.setText("Music Name :"+c.getName());
                    viewHolder3.Artist.setText("Music Artist :"+c.getArtist());
                    viewHolder3.genre.setText("Music Genre :"+c.getGenre().toString());
                    viewHolder3.Album.setText("Music Album :"+c.getAlbum());
                    viewHolder3.i.setBackgroundResource(R.drawable.music);
                    Glide.with(context)
                            .load(c.getName())
                            .into(viewHolder3.i);

                    break;
                case 4:

                    ViewHolder4 viewHolder4=(ViewHolder4)holder;
                    Series s = serieslist.get(position);
                    viewHolder4.Name.setText("Series Name :"+s.getName());
                    viewHolder4.Actor.setText("Favorite Actor :"+s.getFavActor());
                    viewHolder4.genre.setText("Series Genre :"+s.getGenre().toString());
                    viewHolder4.i.setBackgroundResource(R.drawable.series);
                    Glide.with(context)
                            .load(s.getName())
                            .into(viewHolder4.i);
                    break;
            }

            



    }

        @Override
        public int getItemCount () {

            switch (n){
                case 0:return List.size();
                case 1: return aList.size();
                case 2: return booklist.size();
                case 3: return musiclist.size();
                case 4: return serieslist.size();
            }
         return 0;
    }



    }
