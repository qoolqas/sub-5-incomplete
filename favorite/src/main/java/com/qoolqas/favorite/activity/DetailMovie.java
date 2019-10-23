package com.qoolqas.favorite.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
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
import com.qoolqas.favorite.data.Movie;
import com.qoolqas.favorite.R;

import static com.qoolqas.favorite.database.FavoriteContract.MovieColumns.CONTENT_URI;

public class DetailMovie extends AppCompatActivity {
    TextView title, synopsis, rating, release;
    ImageView imageView;
    ProgressBar progressBar;
    Movie movie;

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


            final MaterialFavoriteButton materialFavoriteButton1 = findViewById(R.id.unfavorite);
            SharedPreferences sharedPreferences1 = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            materialFavoriteButton1.setOnFavoriteChangeListener(
                    new MaterialFavoriteButton.OnFavoriteChangeListener() {
                        @Override
                        public void onFavoriteChanged(MaterialFavoriteButton buttonView, boolean favorite) {
                            if (favorite) {
                                getContentResolver().delete(Uri.parse(CONTENT_URI+"/"+movie.getId()), null, null);
                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                startActivity(intent);
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


}
