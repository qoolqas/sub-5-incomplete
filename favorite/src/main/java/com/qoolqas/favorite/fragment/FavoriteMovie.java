package com.qoolqas.favorite.fragment;

import android.database.Cursor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qoolqas.favorite.adapter.MovieAdapter;
import com.qoolqas.favorite.R;

import java.util.Objects;

import static com.qoolqas.favorite.database.FavoriteContract.MovieColumns.CONTENT_URI;

public class FavoriteMovie extends Fragment {
    Cursor mCursor;

    private MovieAdapter adapter;
    public FavoriteMovie() {
        //empty
    }

    View v;


    RecyclerView recyclerview;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_favorite_movie, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);



        recyclerview  = view.findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));


        new Get().execute();

    }

    @Override
    public void onResume() {
        super.onResume();
//        ArrayList<Movie> movie = favoriteHelper.getAllMovies();
//        adapter.setMovies(movie);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
//        favoriteHelper.close();
    }

    private class Get extends AsyncTask<Void,Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... voids) {
            return Objects.requireNonNull(getContext()).getContentResolver().query(CONTENT_URI, null, null, null, null);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            adapter = new MovieAdapter(getContext());
            recyclerview.setAdapter(adapter);
            mCursor = cursor;
            adapter.setmCursor(mCursor);
            adapter.notifyDataSetChanged();


        }
    }
}
