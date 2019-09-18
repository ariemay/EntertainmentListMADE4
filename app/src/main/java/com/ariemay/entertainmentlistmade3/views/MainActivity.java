package com.ariemay.entertainmentlistmade3.views;

import android.app.ProgressDialog;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.ariemay.entertainmentlistmade3.R;
import com.ariemay.entertainmentlistmade3.adapters.MoviesAdapter;
import com.ariemay.entertainmentlistmade3.adapters.TVAdapter;
import com.ariemay.entertainmentlistmade3.adapters.ViewAdapter;
import com.ariemay.entertainmentlistmade3.models.Movies;
import com.ariemay.entertainmentlistmade3.models.TV;
import com.ariemay.entertainmentlistmade3.services.viewmodels.MoviesViewModel;
import com.ariemay.entertainmentlistmade3.services.viewmodels.TVViewModel;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tabLayout = findViewById(R.id.tablayout);
        viewPager = findViewById(R.id.viewpager);
        String type = String.format(getResources().getString(R.string.movies));
        String type1 = String.format(getResources().getString(R.string.tv_show));
        String type2 = String.format(getResources().getString(R.string.favorite));
        adapter = new ViewAdapter(getSupportFragmentManager());

        adapter.AddFragment(new MoviesFragment(), "Movies");
        adapter.AddFragment(new TVListFragment(), "TV Show");
        adapter.AddFragment(new FavoriteActivity(), "Favorites");
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.getTabAt(0).setIcon(R.drawable.movie_icon);
        tabLayout.getTabAt(1).setIcon(R.drawable.tv_icon);
        tabLayout.getTabAt(2).setIcon(R.drawable.favorite_image);
        tabLayout.getTabAt(0).setText(type);
        tabLayout.getTabAt(1).setText(type1);
        tabLayout.getTabAt(2).setText(type2);
        setTitle("Entertainment List");

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_change_setting) {
            Intent mIntent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(mIntent);
        }
        return super.onOptionsItemSelected(item);
    }

}
