package com.qoolqas.moviecataloguesqlfinal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qoolqas.moviecataloguesqlfinal.adapter.TvAdapter;
import com.qoolqas.moviecataloguesqlfinal.data.TvShow;
import com.qoolqas.moviecataloguesqlfinal.database.FavoriteHelper;
import com.qoolqas.moviecataloguesqlfinal.R;

import java.util.ArrayList;

public class FavoriteTv extends Fragment {

    private TvAdapter adapter;
    private FavoriteHelper favoriteHelper;

    public FavoriteTv() {
        //empty
    }

    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_favorite_tv, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        favoriteHelper = FavoriteHelper.getInstance(view.getContext());
        favoriteHelper.open();

        RecyclerView recyclerview = view.findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));

        adapter = new TvAdapter(view.getContext());
        recyclerview.setAdapter(adapter);

    }

    @Override
    public void onResume() {
        super.onResume();
        ArrayList<TvShow> tvShow = favoriteHelper.getAllTv();
        adapter.setTv(tvShow);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        favoriteHelper.close();
    }
}
