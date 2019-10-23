package com.qoolqas.moviecataloguesqlfinal.activity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.github.ivbaranov.mfb.MaterialFavoriteButton;
import com.google.android.material.snackbar.Snackbar;
import com.qoolqas.moviecataloguesqlfinal.data.TvShow;
import com.qoolqas.moviecataloguesqlfinal.database.FavoriteHelper;
import com.qoolqas.moviecataloguesqlfinal.R;

public class DetailTv extends AppCompatActivity {
    TextView title, synopsis, rating, release;
    ImageView imageView;
    ProgressBar progressBar;
    TvShow tvShow;
    FavoriteHelper favoriteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_tv);

        progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);

        title = findViewById(R.id.txt_name2);
        synopsis = findViewById(R.id.txt_synopsis2);
        rating = findViewById(R.id.txt_vote2);
        release = findViewById(R.id.txt_release2);
        imageView = findViewById(R.id.img_detail2);
        progressBar = findViewById(R.id.pb2);

        tvShow = getIntent().getParcelableExtra("tv");
        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();
        {
            progressBar.setVisibility(View.VISIBLE);
            String imageView1 = tvShow.getPosterPath();
            String title1 = tvShow.getTitle();
            String synopsis1 = tvShow.getOverview();
            String rating1 = Double.toString(tvShow.getVoteAverage());
            String release1 = tvShow.getReleaseDate();

            String image_url = "https://image.tmdb.org/t/p/w185" + imageView1;

            Glide.with(this).load(image_url).placeholder(R.drawable.load).into(imageView);
            title.setText(title1);
            synopsis.setText(synopsis1);
            rating.setText(rating1);
            release.setText(release1);

            final MaterialFavoriteButton materialFavoriteButton = findViewById(R.id.favorite_button);
            SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            materialFavoriteButton.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                            if (favorite) {
                                saveFavorite();

                                SharedPreferences.Editor editor = getSharedPreferences("com.qoolqas.moviecataloguesql.DetailTv", MODE_PRIVATE).edit();
                                editor.putBoolean("Favorite Added", true);
                                editor.apply();
                                Snackbar.make(buttonView, getString(R.string.added),
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
            );
            final MaterialFavoriteButton materialFavoriteButton1 = findViewById(R.id.unfavorite);
            SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            materialFavoriteButton1.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                            if (favorite) {
                                favoriteHelper = new FavoriteHelper(DetailTv.this);
                                favoriteHelper.open();
                                favoriteHelper.deleteTv(tvShow.getId());
                                SharedPreferences.Editor editor = getSharedPreferences("com.qoolqas.moviecataloguesql.DetailTv", MODE_PRIVATE).edit();
                                editor.putBoolean("Favorite Removed", true);
                                editor.apply();

                                Snackbar.make(buttonView, getString(R.string.removed),
                                        Snackbar.LENGTH_SHORT).show();
                            }
                        }
                    }
            );

        }
        progressBar.setVisibility(View.GONE);
    }
    public void saveFavorite() {
        favoriteHelper = new FavoriteHelper(this);
        int tv_id = tvShow.getId();
        String poster = tvShow.getPosterPath();
        String title = tvShow.getTitle();
        String rating = Double.toString(tvShow.getVoteAverage());
        String synopsis = tvShow.getOverview();
        String release = tvShow.getReleaseDate();

        tvShow.setId(tv_id);
        tvShow.setPosterPath(poster);
        tvShow.setTitle(title);
        tvShow.setVoteAverage(Double.parseDouble(rating));
        tvShow.setOverview(synopsis);
        tvShow.setReleaseDate(release);

        favoriteHelper.open();
        favoriteHelper.insertTv(tvShow);
//        favoriteHelper.close();

    }
}