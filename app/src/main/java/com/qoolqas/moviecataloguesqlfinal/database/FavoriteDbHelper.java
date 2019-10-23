package com.qoolqas.moviecataloguesqlfinal.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.MovieColumns.TABLE_MOVIE;
import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.TvColumns.TABLE_TV;

public class FavoriteDbHelper extends SQLiteOpenHelper {

    private static final String MOVIE_DATABASE_NAME = "favoritemovie";

    private static final int MOVIE_DATABASE_VERSION = 1;

    private static final String SQL_CREATE_TABLE_MOVIE = String.format("CREATE TABLE %s" +
                    " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s INTEGER NOT NULL UNIQUE," +
                    " %s TEXT NOT NULL UNIQUE," +
                    " %s TEXT NOT NULL UNIQUE," +
                    " %s TEXT NOT NULL UNIQUE," +
                    " %s TEXT NOT NULL UNIQUE," +
                    " %s TEXT NOT NULL UNIQUE)",
            TABLE_MOVIE,
            FavoriteContract.MovieColumns._ID,
            FavoriteContract.MovieColumns.ID,
            FavoriteContract.MovieColumns.TITLE,
            FavoriteContract.MovieColumns.OVERVIEW,
            FavoriteContract.MovieColumns.RELEASE,
            FavoriteContract.MovieColumns.USER_RATING,
            FavoriteContract.MovieColumns.POSTER_PATH
            );
    private static final String SQL_CREATE_TV_TABLE = String.format("CREATE TABLE %s"+
                    " (%s INTEGER PRIMARY KEY AUTOINCREMENT," +
                    " %s INTEGER NOT NULL UNIQUE," +
                    " %s TEXT NOT NULL UNIQUE," +
                    " %s TEXT NOT NULL UNIQUE," +
                    " %s TEXT NOT NULL UNIQUE," +
                    " %s TEXT NOT NULL UNIQUE," +
                    " %s TEXT NOT NULL UNIQUE)",
            TABLE_TV,
            FavoriteContract.TvColumns._ID,
            FavoriteContract.TvColumns.TVID,
            FavoriteContract.TvColumns.TITLE_TV,
            FavoriteContract.TvColumns.RELEASE_TV,
            FavoriteContract.TvColumns.USER_RATING_TV,
            FavoriteContract.TvColumns.OVERVIEW_TV,
            FavoriteContract.TvColumns.POSTER_PATH_TV
    );

    public FavoriteDbHelper(Context context) {
        super(context, MOVIE_DATABASE_NAME, null, MOVIE_DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_MOVIE);
        sqLiteDatabase.execSQL(SQL_CREATE_TV_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_MOVIE);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_TV);
        onCreate(sqLiteDatabase);
    }
}
