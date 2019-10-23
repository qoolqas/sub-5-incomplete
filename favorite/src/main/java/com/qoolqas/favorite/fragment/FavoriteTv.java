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

import com.qoolqas.favorite.adapter.TvAdapter;
import com.qoolqas.favorite.R;

import java.util.Objects;

import static com.qoolqas.favorite.database.FavoriteContract.TvColumns.CONTENT_URI_TV;

public class FavoriteTv extends Fragment {
    Cursor mCursor;

    private TvAdapter adapter;

    public FavoriteTv() {
        //empty
    }

    View v;
    RecyclerView recyclerview;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.activity_favorite_tv, container, false);
        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        recyclerview= view.findViewById(R.id.recyclerview);
        recyclerview.setHasFixedSize(true);
        recyclerview.setLayoutManager(new LinearLayoutManager(view.getContext()));


        new Get().execute();

    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    private class Get extends AsyncTask<Void,Void, Cursor> {
        @Override
        protected Cursor doInBackground(Void... voids) {
            return Objects.requireNonNull(getContext()).getContentResolver().query(CONTENT_URI_TV, null, null, null, null);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected void onPostExecute(Cursor cursor) {
            super.onPostExecute(cursor);
            adapter = new TvAdapter(getContext());
            recyclerview.setAdapter(adapter);
            mCursor = cursor;
            adapter.setmCursor(mCursor);
            adapter.notifyDataSetChanged();


        }
    }
}
