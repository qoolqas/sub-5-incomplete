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
import com.qoolqas.moviecataloguesqlfinal.data.Movie;
import com.qoolqas.moviecataloguesqlfinal.database.FavoriteHelper;
import com.qoolqas.moviecataloguesqlfinal.R;

public class DetailMovie extends AppCompatActivity {
    TextView title, synopsis, rating, release;
    ImageView imageView;
    ProgressBar progressBar;
    Movie movie;
    FavoriteHelper favoriteHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail_movie);

        progressBar = new ProgressBar(this);
        progressBar.setIndeterminate(true);

        title = findViewById(R.id.txt_name1);
        synopsis = findViewById(R.id.txt_synopsis1);
        rating = findViewById(R.id.txt_vote1);
        release = findViewById(R.id.txt_release1);
        imageView = findViewById(R.id.img_detail1);
        progressBar = findViewById(R.id.pb1);

        movie = getIntent().getParcelableExtra("movies");
        favoriteHelper = FavoriteHelper.getInstance(getApplicationContext());
        favoriteHelper.open();
        {
            progressBar.setVisibility(View.VISIBLE);
            String imageView1 = movie.getPosterPath();
            String title1 = movie.getTitle();
            String synopsis1 = movie.getOverview();
            String rating1 = Double.toString(movie.getVoteAverage());
            String release1 = movie.getReleaseDate();

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

                                SharedPreferences.Editor editor = getSharedPreferences("com.qoolqas.moviecataloguesql.DetailMovie", MODE_PRIVATE).edit();
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
                                favoriteHelper = new FavoriteHelper(DetailMovie.this);
                                favoriteHelper.open();
                                favoriteHelper.deleteMovie(movie.getId());
//                              favoriteHelper.close();
                                SharedPreferences.Editor editor = getSharedPreferences("com.qoolqas.moviecataloguesql.DetailMovie", MODE_PRIVATE).edit();
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
        int movie_id = movie.getId();
        String poster = movie.getPosterPath();
        String title = movie.getTitle();
        String rating = Double.toString(movie.getVoteAverage());
        String synopsis = movie.getOverview();
        String release = movie.getReleaseDate();

        movie.setId(movie_id);
        movie.setPosterPath(poster);
        movie.setTitle(title);
        movie.setVoteAverage(Double.parseDouble(rating));
        movie.setOverview(synopsis);
        movie.setReleaseDate(release);

        favoriteHelper.open();
        favoriteHelper.insertMovie(movie);
//        favoriteHelper.close();

    }


}
