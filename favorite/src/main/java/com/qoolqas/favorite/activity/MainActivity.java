package com.qoolqas.favorite.activity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.qoolqas.favorite.adapter.ViewPagerAdapter;
import com.qoolqas.favorite.fragment.FavoriteMovie;
import com.qoolqas.favorite.fragment.FavoriteTv;
import com.qoolqas.favorite.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TabLayout tabLayout = findViewById(R.id.tablayout_id1);
        ViewPager viewPager = findViewById(R.id.viewpager_id1);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());

        adapter.AddFragment(new FavoriteMovie(), getString(R.string.moviefav));
        adapter.AddFragment(new FavoriteTv(),getString(R.string.tvfav));
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);


        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.setElevation(0);

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Favorite App");
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.action_change_settings){
            Intent intent = new Intent(Settings.ACTION_LOCALE_SETTINGS);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
