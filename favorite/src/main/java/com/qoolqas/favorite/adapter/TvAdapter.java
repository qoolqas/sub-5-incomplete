package com.qoolqas.favorite.adapter;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.qoolqas.favorite.activity.DetailTv;
import com.qoolqas.favorite.data.TvShow;
import com.qoolqas.favorite.R;

import java.util.ArrayList;
import java.util.List;

public class TvAdapter extends RecyclerView.Adapter<TvAdapter.ViewHolderTv> {
    private Context mContext;
    private List<TvShow> mlist;
    private Cursor mCursor;

    public TvAdapter(Context mContext, List<TvShow> mList) {
        this.mContext = mContext;
        this.mlist = mList;
    }
    public void setmCursor(Cursor cursor){
        this.mCursor = cursor;
    }
    private TvShow Item(int i){
        if (!mCursor.moveToPosition(i)){
            throw  new IllegalStateException("Failed");
        }
        return new TvShow(mCursor);
    }
    public TvAdapter(Context context) {
        this.mContext = context;
    }
    public void setTv(ArrayList<TvShow> mlist) {
        this.mlist = mlist;
        notifyDataSetChanged();
    }

    class ViewHolderTv extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView title, release, rating;

        ViewHolderTv(@NonNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.txt_name);
            release = itemView.findViewById(R.id.txt_release);
            rating = itemView.findViewById(R.id.txt_vote);
            imageView = itemView.findViewById(R.id.img_movies);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position = getAdapterPosition();
                    if (position != RecyclerView.NO_POSITION) {
                        TvShow clickedItem = mlist.get(position);
                        Intent intent = new Intent(mContext, DetailTv.class);
                        intent.putExtra("tv",clickedItem);
                        mContext.startActivity(intent);
                        Toast.makeText(view.getContext(), clickedItem.getTitle(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @NonNull
    @Override
    public ViewHolderTv onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        view = LayoutInflater.from(mContext).inflate(R.layout.card, parent, false);
        ViewHolderTv vHolder = new ViewHolderTv(view);
        return vHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderTv holder, final int position) {
        final TvShow tvShow = Item(position);
        holder.title.setText(tvShow.getTitle());
        holder.release.setText(tvShow.getReleaseDate());
        holder.rating.setText(String.valueOf(tvShow.getVoteAverage()));
        Log.d("gg",tvShow.getOverview());
        Glide.with(mContext).load("https://image.tmdb.org/t/p/w185" + tvShow.getPosterPath()).into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        if (mCursor == null) return 0;
        return mCursor.getCount();
    }
}
