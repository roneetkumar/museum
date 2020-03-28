package com.example.museum;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private androidx.appcompat.widget.Toolbar mToolbar;

    private ViewPager viewPager;
    private TabLayout tabLayout;

    private Albums albums;
    private Artists artists;
    private Songs songs;
    private Playlists playlists;
    private Genres genres;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        albums = new Albums();
        artists = new Artists();
        songs = new Songs();
        playlists = new Playlists();
        genres = new Genres();

        viewPager = findViewById(R.id.view_pager);
        tabLayout= findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager(),0);
        viewPagerAdapter.addFragment(songs,getString(R.string.tab1));
        viewPagerAdapter.addFragment(albums,getString(R.string.tab2));
        viewPagerAdapter.addFragment(artists,getString(R.string.tab3));
        viewPagerAdapter.addFragment(genres,getString(R.string.tab4));
        viewPagerAdapter.addFragment(playlists,getString(R.string.tab5));

        viewPager.setAdapter(viewPagerAdapter);

        mToolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(mToolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        navigationView= findViewById(R.id.nav_view);


        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(
                this,
                drawerLayout,
                mToolbar,
                R.string.openNav,
                R.string.closeNav
        );

        drawerLayout.addDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
         super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        return false;
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private List<Fragment> fragments = new ArrayList<>();
        private List<String> titles = new ArrayList<>();


        public ViewPagerAdapter(@NonNull FragmentManager fm, int behavior) {
            super(fm, behavior);
        }

        public void addFragment(Fragment fragment, String title){
            fragments.add(fragment);
            titles.add(title);
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return titles.get(position);
        }
    }
}
