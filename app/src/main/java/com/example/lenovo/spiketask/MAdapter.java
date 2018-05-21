package com.example.lenovo.spiketask;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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

    private int n;

    public class ViewHolder0 extends RecyclerView.ViewHolder {//movie
        public TextView Name, Actor, genre;
        ImageView i;
        public ViewHolder0(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            genre = (TextView) view.findViewById(R.id.genre);
            Actor=(TextView) view.findViewById(R.id.AuthorArtist);
            i=(ImageView)view.findViewById(R.id.icon);

        }


    }
    public class ViewHolder1 extends RecyclerView.ViewHolder {//article
        public TextView Name, Author,Source,date, genre;
        ImageView i;
        public ViewHolder1(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            genre = (TextView) view.findViewById(R.id.genre);
            Author=(TextView) view.findViewById(R.id.AuthorArtist);
            Source=(TextView)view.findViewById(R.id.Source);
            date=(TextView)view.findViewById(R.id.Date);
            i=(ImageView)view.findViewById(R.id.icon);


        }


    }
    public class ViewHolder2 extends RecyclerView.ViewHolder {//book
        public TextView Name, Author, genre;
        ImageView i;
        public ViewHolder2(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            genre = (TextView) view.findViewById(R.id.genre);
            Author=(TextView) view.findViewById(R.id.AuthorArtist);
            i=(ImageView)view.findViewById(R.id.icon);
        }


    }
    public class ViewHolder3 extends RecyclerView.ViewHolder {//music
        public TextView Name, Artist,Album, genre;
        ImageView i;
        public ViewHolder3(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            genre = (TextView) view.findViewById(R.id.genre);
            Artist=(TextView) view.findViewById(R.id.AuthorArtist);
            Album=(TextView)view.findViewById(R.id.Source);
            i=(ImageView)view.findViewById(R.id.icon);
        }


    }
    public class ViewHolder4 extends RecyclerView.ViewHolder {//series
        public TextView Name, Actor, genre;
        ImageView i;
        public ViewHolder4(View view) {
            super(view);
            Name = (TextView) view.findViewById(R.id.Name);
            genre = (TextView) view.findViewById(R.id.genre);
            Actor=(TextView) view.findViewById(R.id.AuthorArtist);
            i=(ImageView)view.findViewById(R.id.icon);
        }


    }
    @Override
    public int getItemViewType(int position) {
        // Just as an example, return 0 or 2 depending on position
        // Note that unlike in ListView adapters, types don't have to be contiguous

        return n;
    }
//movieList,bookList,seriesList,articleList,musicList,0
    public MAdapter(List<Movie> moviesList, List<Book> b, List<Series> s, List<Article> a, List<Music> m ,int n) {
        this.List = moviesList;
        booklist=b;
        serieslist=s;
        aList=a;
        musiclist=m;
        this.n=n;

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
                    viewHolder0.Name.setText(m.getName());
                    viewHolder0.genre.setText(m.getGenre().toString());
                    viewHolder0.Actor.setText(m.getFavActor());
                    viewHolder0.i.setBackgroundResource(R.drawable.movie);



                break;
                case 1:
                    ViewHolder1 viewHolder1 = (ViewHolder1)holder;
                    Article a = aList.get(position);

                    viewHolder1.Name.setText(a.getName());
                    viewHolder1.genre.setText(a.getGenre().toString());//m.getGenre().toString()
                    viewHolder1.Author.setText(a.getAuthor());
                    viewHolder1.Source.setText(a.getSource());
                    viewHolder1.date.setText(a.getPublishedDate());
                    viewHolder1.i.setBackgroundResource(R.drawable.article);

                break;
                case 2: ViewHolder2 viewHolder2=(ViewHolder2)holder;
                Book b = booklist.get(position);
                viewHolder2.Name.setText(b.getName());
                viewHolder2.Author.setText(b.getAuthor());
                viewHolder2.genre.setText(b.getGenre().toString());
                    viewHolder2.i.setBackgroundResource(R.drawable.book);
                break;
                case 3:ViewHolder3 viewHolder3=(ViewHolder3)holder;
                    Music c = musiclist.get(position);
                    viewHolder3.Name.setText(c.getName());
                    viewHolder3.Artist.setText(c.getArtist());
                    viewHolder3.genre.setText(c.getGenre().toString());
                    viewHolder3.Album.setText(c.getAlbum());
                    viewHolder3.i.setBackgroundResource(R.drawable.music);

                    break;
                case 4:

                    ViewHolder4 viewHolder4=(ViewHolder4)holder;
                    Series s = serieslist.get(position);
                    viewHolder4.Name.setText(s.getName());
                    viewHolder4.Actor.setText(s.getFavActor());
                    viewHolder4.genre.setText(s.getGenre().toString());
                    viewHolder4.i.setBackgroundResource(R.drawable.series);
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
