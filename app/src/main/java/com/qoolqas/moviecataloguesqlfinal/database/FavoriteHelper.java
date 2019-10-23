package com.qoolqas.moviecataloguesqlfinal.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.qoolqas.moviecataloguesqlfinal.data.Movie;
import com.qoolqas.moviecataloguesqlfinal.data.TvShow;

import java.util.ArrayList;

import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.MovieColumns.ID;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.MovieColumns.OVERVIEW;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.MovieColumns.POSTER_PATH;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.MovieColumns.RELEASE;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.MovieColumns.TABLE_MOVIE;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.MovieColumns.TITLE;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.MovieColumns.USER_RATING;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.TvColumns.OVERVIEW_TV;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.TvColumns.POSTER_PATH_TV;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.TvColumns.RELEASE_TV;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.TvColumns.TABLE_TV;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.TvColumns.TITLE_TV;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.TvColumns.TVID;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.TvColumns.USER_RATING_TV;

public class FavoriteHelper {
    private static final String DATABASE_TABLE = TABLE_MOVIE;
    private static final String DATABASE_TABLE_TV = TABLE_TV;
    private static FavoriteDbHelper favoriteDbHelper;
    private static FavoriteHelper INSTANCE;

    private static SQLiteDatabase database;

    public FavoriteHelper(Context context) {
        favoriteDbHelper = new FavoriteDbHelper(context);
    }

    public static FavoriteHelper getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (SQLiteOpenHelper.class) {
                if (INSTANCE == null) {
                    INSTANCE = new FavoriteHelper(context);
                }
            }
        }
        return INSTANCE;
    }

    public void open() throws SQLException {
        database = favoriteDbHelper.getWritableDatabase();
    }

    public void close() {
        favoriteDbHelper.close();

        if (database.isOpen())
            database.close();
    }

    public ArrayList<Movie> getAllMovies() {
        ArrayList<Movie> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE, null,
                null,
                null,
                null,
                null,
                ID + " ASC",
                null);
        cursor.moveToFirst();
        Movie movie;
        if (cursor.getCount() > 0) {
            do {
                movie = new Movie(cursor);
                movie.setId(cursor.getInt(cursor.getColumnIndexOrThrow(ID)));
                movie.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE_TV)));
                movie.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW)));
                movie.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE)));
                movie.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(USER_RATING)));
                movie.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH)));

                arrayList.add(movie);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertMovie(Movie movie) {
        ContentValues args = new ContentValues();
        args.put(ID, movie.getId());
        args.put(TITLE, movie.getTitle());
        args.put(OVERVIEW, movie.getOverview());
        args.put(RELEASE, movie.getReleaseDate());
        args.put(USER_RATING, movie.getVoteAverage());
        args.put(POSTER_PATH, movie.getPosterPath());

        return database.insert(DATABASE_TABLE, null, args);
    }

    public int deleteMovie(int id) {
        return database.delete(TABLE_MOVIE, ID + " = '" + id + "'", null);
    }

    public ArrayList<TvShow> getAllTv() {
        ArrayList<TvShow> arrayList = new ArrayList<>();
        Cursor cursor = database.query(DATABASE_TABLE_TV, null,
                null,
                null,
                null,
                null,
                TVID + " ASC",
                null);
        cursor.moveToFirst();
        TvShow tvShow;
        if (cursor.getCount() > 0) {
            do {
                tvShow = new TvShow();
                tvShow.setId(cursor.getInt(cursor.getColumnIndexOrThrow(TVID)));
                tvShow.setTitle(cursor.getString(cursor.getColumnIndexOrThrow(TITLE_TV)));
                tvShow.setOverview(cursor.getString(cursor.getColumnIndexOrThrow(OVERVIEW_TV)));
                tvShow.setReleaseDate(cursor.getString(cursor.getColumnIndexOrThrow(RELEASE_TV)));
                tvShow.setVoteAverage(cursor.getDouble(cursor.getColumnIndexOrThrow(USER_RATING_TV)));
                tvShow.setPosterPath(cursor.getString(cursor.getColumnIndexOrThrow(POSTER_PATH_TV)));

                arrayList.add(tvShow);
                cursor.moveToNext();

            } while (!cursor.isAfterLast());
        }
        cursor.close();
        return arrayList;
    }

    public long insertTv(TvShow tvshow) {
        ContentValues args = new ContentValues();
        args.put(TVID, tvshow.getId());
        args.put(TITLE_TV, tvshow.getTitle());
        args.put(OVERVIEW_TV, tvshow.getOverview());
        args.put(RELEASE_TV, tvshow.getReleaseDate());
        args.put(USER_RATING_TV, tvshow.getVoteAverage());
        args.put(POSTER_PATH_TV, tvshow.getPosterPath());


        return database.insert(DATABASE_TABLE_TV, null, args);
    }

    public int deleteTv(int id) {
        return database.delete(TABLE_TV, TVID + " = '" + id + "'", null);
    }

    public Cursor MovieProvider() {
        return database.query(DATABASE_TABLE, null, null, null, null, null, null);
    }

    public Cursor TvProvider() {
        return database.query(DATABASE_TABLE_TV, null, null, null, null, null, null);
    }
    public int deleteProvider(String id){
        return database.delete(DATABASE_TABLE, FavoriteContract.MovieColumns._ID + "=?", new String[]{id});
    }
    public int deleteProviderTv(String idt){
        return database.delete(DATABASE_TABLE_TV, FavoriteContract.TvColumns._ID + "?=", new String[]{idt});
    }

}
