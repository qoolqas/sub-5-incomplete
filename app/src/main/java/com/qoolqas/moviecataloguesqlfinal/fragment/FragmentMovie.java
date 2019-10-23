package com.qoolqas.moviecataloguesqlfinal.fragment;

import android.app.SearchManager;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qoolqas.moviecataloguesqlfinal.adapter.MovieAdapter;
import com.qoolqas.moviecataloguesqlfinal.data.Movie;
import com.qoolqas.moviecataloguesqlfinal.R;
import com.qoolqas.moviecataloguesqlfinal.vm.MovieModel;

public class FragmentMovie extends Fragment {
    private MovieAdapter movieAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private MovieModel movieModel;

    public FragmentMovie() {
        //empty
    }

    View v;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.movie_fragment, container, false);
        recyclerView = v.findViewById(R.id.recyclerview);
        setHasOptionsMenu(true);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        progressBar = v.findViewById(R.id.pb);
        movieModel = ViewModelProviders.of(this).get(MovieModel.class);
        movieModel.liveMovies().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                Toast.makeText(getContext(), getString(R.string.orientation), Toast.LENGTH_SHORT).show();
                movieAdapter = new MovieAdapter(getContext(), movie.getResults());
                recyclerView.setAdapter(movieAdapter);
                progressBar.setVisibility(View.GONE);
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        return v;
    }

    @Override
    public void onCreateOptionsMenu(@NonNull Menu menu, @NonNull MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        menu.clear();
        inflater.inflate(R.menu.main_menu, menu);

        SearchManager searchManager = (SearchManager) getActivity().getSystemService(Context.SEARCH_SERVICE);

        if (searchManager != null) {
            SearchView searchView = (SearchView) (menu.findItem(R.id.search)).getActionView();
            searchView.setSearchableInfo(searchManager.getSearchableInfo(getActivity().getComponentName()));
            searchView.setQueryHint(getResources().getString(R.string.search));
            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String query) {
                    Toast.makeText(getContext(), query, Toast.LENGTH_SHORT).show();
                    return true;
                }

                @Override
                public boolean onQueryTextChange(String newText) {
                    return false;
                }
            });
        }
    }

}

