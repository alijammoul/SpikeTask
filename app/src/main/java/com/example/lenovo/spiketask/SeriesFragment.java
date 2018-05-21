package com.example.lenovo.spiketask;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.lenovo.spiketask.Models.Article;
import com.example.lenovo.spiketask.Models.Book;
import com.example.lenovo.spiketask.Models.Movie;
import com.example.lenovo.spiketask.Models.Music;
import com.example.lenovo.spiketask.Models.Series;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class SeriesFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    ArrayList<Movie> list=new ArrayList<>();

    private List<Book> bookList = new ArrayList<>();
    private List<Series> seriesList = new ArrayList<>();
    private List<Article> articleList = new ArrayList<>();
    private List<Music> musicList = new ArrayList<>();


    RecyclerView rv;
    MAdapter mAdapter;
    private FirebaseFirestore fs;
    View vv;
    FirebaseAuth fba;
    FirebaseUser user;
    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public SeriesFragment() {
        // Required empty public constructor
    }


    public static SeriesFragment newInstance(String param1, String param2) {
        SeriesFragment fragment = new SeriesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fba=FirebaseAuth.getInstance();
        user=fba.getCurrentUser();
        fs = FirebaseFirestore.getInstance();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        FillList();
        vv = inflater.inflate(R.layout.fragment_series, container, false);

        // lv = inflater.inflate(R.layout.activity_check, container, false).findViewById(R.id.list);
        rv = (RecyclerView) vv.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        mAdapter = new MAdapter(list,bookList,seriesList,articleList,musicList,4,getActivity().getApplicationContext());
        rv.setAdapter(mAdapter);
        rv.setItemAnimator(new DefaultItemAnimator());




        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), rv ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view,final int position) {


                        fs.collection("Users").document(user.getEmail()).collection("Series").document(seriesList.get(position).getId())//list.get(position).getId()
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        seriesList.remove(position); mAdapter.notifyDataSetChanged();

                                        Log.d("Delete operation", "DocumentSnapshot successfully deleted!");
                                        Toast.makeText(getActivity().getApplicationContext(),"Deleted"+position,Toast.LENGTH_SHORT).show();
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Log.w("Delete operation", "Error deleting document", e);
                                    }
                                });
                    }

                    @Override public void onLongItemClick(View view, int position) {
                        // later

                    }
                })
        );






        return vv;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }


    private void FillList() {
        if(seriesList.size()>0)
           seriesList.clear();
/*
        fs.collection("Users").document(user.getEmail()).collection("Movie").
                addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(QuerySnapshot documentSnapshots, FirebaseFirestoreException e) {
                        if(e!= null){
                            Log.d("Error", "Error:"+ e.getMessage());
                        }

                        for (DocumentChange doc : documentSnapshots.getDocumentChanges()) {
                                Movie m= doc.getDocument().toObject(Movie.class);
                               // Log.d("blablax1",m.getName().toString());

                                list.add(m);
                                mAdapter.notifyDataSetChanged();
                        }
                    }
                });*/


        fs.collection("Users").document(user.getEmail()).collection("Series").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q :task.getResult()){
                            Series m =q.toObject(Series.class);
                            m.setId(q.getId());

                            //Movie m = new Movie(q.getString("name"),q.getString("favActor"), MovieGenre.Romance);//MovieGenre.map(q.getString("Genre"))
                            seriesList.add(m);
                           // Log.d("Data",m.getName()+"       test here    " + m.getGenre()+"   "+m.getFavActor());

                        }
                        mAdapter.notifyDataSetChanged();


                    }
                }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getActivity().getApplicationContext(),"fail",Toast.LENGTH_SHORT).show();

            }
        });

    }
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
