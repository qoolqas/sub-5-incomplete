package com.qoolqas.moviecataloguesqlfinal.data;

import android.database.Cursor;
import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;
import com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract;

import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.getColumn;

public class WidgetItem implements Parcelable {
    @SerializedName("title")
    private String title;
    @SerializedName("poster_path")
    private String posterPath;

    public WidgetItem(Cursor cursor) {
        this.title = getColumn(cursor, FavoriteContract.MovieColumns.TITLE);
        this.posterPath = getColumn(cursor, FavoriteContract.MovieColumns.POSTER_PATH);
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.title);
        dest.writeString(this.posterPath);
    }

    protected WidgetItem(Parcel in) {
        this.title = in.readString();
        this.posterPath = in.readString();
    }

    public static final Parcelable.Creator<WidgetItem> CREATOR = new Parcelable.Creator<WidgetItem>() {
        @Override
        public WidgetItem createFromParcel(Parcel source) {
            return new WidgetItem(source);
        }

        @Override
        public WidgetItem[] newArray(int size) {
            return new WidgetItem[size];
        }
    };
}
