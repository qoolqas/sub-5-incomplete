package com.qoolqas.moviecataloguesqlfinal.database;

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
        static final String OVERVIEW = "overview";
        static final String RELEASE= "release_date";
        static final String USER_RATING = "vote_average";
        public static final String POSTER_PATH = "posterpath";
        public static final Uri CONTENT_URI = new Uri.Builder()
                .scheme(SCHEME)
                .authority(AUTHORITY)
                .appendPath(TABLE_MOVIE)
                .build();
    }
    static final class TvColumns implements BaseColumns{
        static String TABLE_TV = "tv";

        static final String TVID = "id";
        static final String TITLE_TV = "title";
        static final String OVERVIEW_TV = "overview";
        static final String RELEASE_TV = "first_air_date";
        static final String USER_RATING_TV = "vote_average";
        static final String POSTER_PATH_TV = "posterpath";
    }
    public static String getColumn(Cursor cursor, String column){
        return cursor.getString(cursor.getColumnIndex(column));
    }
}
