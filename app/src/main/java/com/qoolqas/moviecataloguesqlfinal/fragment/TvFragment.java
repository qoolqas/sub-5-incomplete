package com.qoolqas.moviecataloguesqlfinal.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.qoolqas.moviecataloguesqlfinal.adapter.TvAdapter;
import com.qoolqas.moviecataloguesqlfinal.data.TvShow;
import com.qoolqas.moviecataloguesqlfinal.R;
import com.qoolqas.moviecataloguesqlfinal.vm.TvModel;

public class TvFragment extends Fragment {
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TvAdapter tvAdapter;
    private TvModel tvModel;

    public TvFragment(){
        //empty
    }


    View v;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.tv_fragment,container,false);
        recyclerView = v.findViewById(R.id.recyclerview);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));

        progressBar = v.findViewById(R.id.pb);
        tvModel = ViewModelProviders.of(this).get(TvModel.class);
        tvModel.liveTv().observe(this, new Observer<TvShow>() {
            @Override
            public void onChanged(TvShow tvShow) {
                Toast.makeText(getContext(), getString(R.string.orientation), Toast.LENGTH_SHORT).show();
                tvAdapter = new TvAdapter(getContext(),tvShow.getResults());
                recyclerView.setAdapter(tvAdapter);
                progressBar.setVisibility(View.GONE);
            }
        });

        progressBar.setVisibility(View.VISIBLE);
        return v;
    }


}
