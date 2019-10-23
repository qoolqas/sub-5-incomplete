package com.qoolqas.favorite.database;

import android.database.Cursor;
import android.net.Uri;
import android.provider.BaseColumns;

public class FavoriteContract {
    public static final class MovieColumns implements BaseColumns {
        public static final String TABLE_MOVIE = "movie";
        public static final String AUTHORITY="com.qoolqas.moviecataloguesqlfinal";
        static final String SCHEME = "content";
        static final String ID = "id";
        public static final String TITLE = "title";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE= "release_date";
        public static final String USER_RATING = "vote_average";
        public static final String POSTER_PATH = "posterpath";
        public static final Uri CONTENT_URI = new Uri.Builder()
                .scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();
    }
    public static final class TvColumns implements BaseColumns{
        static String TABLE_TV = "tv";
        public static final String AUTHORITY="com.qoolqas.moviecataloguesqlfinal";
        public static final String SCHEME = "content";
        public static final String TVID = "id";
        public static final String TITLE_TV = "title";
        public static final String OVERVIEW_TV = "overview";
        public static final String RELEASE_TV = "first_air_date";
        public static final String USER_RATING_TV = "vote_average";
        public static final String POSTER_PATH_TV = "posterpath";
        public static final Uri CONTENT_URI_TV = new Uri.Builder()
                .scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_TV)
                .build();
    }
    public static String getColumn(Cursor cursor, String column){
        return cursor.getString(cursor.getColumnIndex(column));
    }
    public static int getColumnInt(Cursor cursor, String columnInt){
        return cursor.getInt(cursor.getColumnIndex(columnInt));
    }
    public static Double getColumnDouble(Cursor cursor, String columnDouble){
        return cursor.getDouble(cursor.getColumnIndex(columnDouble));
    }
}
