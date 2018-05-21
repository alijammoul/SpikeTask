package com.example.lenovo.spiketask;

import android.app.ProgressDialog;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class CollectionActivity extends AppCompatActivity {



    private FirebaseAuth fba;
    private FirebaseUser user;
    private TabLayout tabLayout;
    public String mode;
    private Bundle b;
    private ProgressDialog d;
    private Toolbar toolbar;
    private ViewPager viewPager;
    private int[] tabIcons = {
            R.drawable.ic_movie_black_24dp,
            R.drawable.ic_book_black_24dp,
            R.drawable.ic_live_tv_black_24dp,
            R.drawable.ic_insert_drive_file_black_24dp,
            R.drawable.ic_music_note_black_24dp
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collection);
        d=new ProgressDialog(getApplicationContext(),R.style.AppCompatAlertDialogStyle);
       mode=getIntent().getStringExtra("Mode");
        Log.d("MODE activity ",mode);

        fba = FirebaseAuth.getInstance();
        user = fba.getCurrentUser();

        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.container);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();
        d.dismiss();
        Toast.makeText(getApplicationContext(),"Tap on Items to delete",Toast.LENGTH_LONG).show();


    }
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        b=new Bundle();
        b.putString("mode",mode);
        MovieFragment m = new MovieFragment();
        Log.d("Last mode inn activity",b.getString("mode"));
        m.setArguments(b);
        ArticleFragment a = new ArticleFragment();
        a.setArguments(b);
        BookFragment bk = new BookFragment();
        bk.setArguments(b);
        SeriesFragment s = new SeriesFragment();
        s.setArguments(b);
        MusicFragment mc = new MusicFragment();
        mc.setArguments(b);
        adapter.addFragment(m, "Movies");
        adapter.addFragment(a, "Articles");
        adapter.addFragment(s, "Series");
        adapter.addFragment(bk, "Books");
        adapter.addFragment(mc, "Music");
        viewPager.setAdapter(adapter);
        toolbar.setTitle(mode);
    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);


    }
    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }
}
