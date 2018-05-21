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



public class BookFragment extends Fragment {

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
    private String mParam1;
    private String mParam2;
    private String mode;
    private OnFragmentInteractionListener mListener;

    public BookFragment() {
        // Required empty public constructor
    }


    public static BookFragment newInstance(String param1, String param2) {
        BookFragment fragment = new BookFragment();
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
        // Inflate the layout for this fragmentF

        vv = inflater.inflate(R.layout.fragment_book, container, false);

        // lv = inflater.inflate(R.layout.activity_check, container, false).findViewById(R.id.list);
        rv = (RecyclerView) vv.findViewById(R.id.recycler_view);
        rv.setLayoutManager(new LinearLayoutManager(getActivity().getApplicationContext()));


        mAdapter = new MAdapter(list,bookList,seriesList,articleList,musicList,2,getActivity().getApplicationContext());
        rv.setAdapter(mAdapter);
        rv.setItemAnimator(new DefaultItemAnimator());

        FillList();

        rv.addOnItemTouchListener(
                new RecyclerItemClickListener(getActivity().getApplicationContext(), rv ,new RecyclerItemClickListener.OnItemClickListener() {
                    @Override public void onItemClick(View view,final int position) {


                        fs.collection("Users").document(user.getEmail()).collection(mode).document("Book").collection("Book").document(bookList.get(position).getId())//list.get(position).getId()
                                .delete()
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        bookList.remove(position); mAdapter.notifyDataSetChanged();

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


    private void FillList() {
        if(bookList.size()>0)
            bookList.clear();

        String unsafemode=getArguments().getString("mode");
        switch(unsafemode){
            case "My Saved Collection":
                mode ="saved";
                break;
            case "View Later": mode="viewedlater";break;

        }
        fs.collection("Users").document(user.getEmail()).collection(mode).document("Book").collection("Book").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for(DocumentSnapshot q :task.getResult()){
                            Book m =q.toObject(Book.class);
                            m.setId(q.getId());

                            //Movie m = new Movie(q.getString("name"),q.getString("favActor"), MovieGenre.Romance);//MovieGenre.map(q.getString("Genre"))
                            bookList.add(m);
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
