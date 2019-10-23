package com.qoolqas.moviecataloguesqlfinal;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.RemoteViewsService;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.qoolqas.moviecataloguesqlfinal.data.WidgetItem;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;

import static com.qoolqas.moviecataloguesqlfinal.database.FavoriteContract.MovieColumns.CONTENT_URI;

public class StackRemoteViewsFactory implements RemoteViewsService.RemoteViewsFactory {

    private final List<Bitmap> mWidgetItems = new ArrayList<>();
    private final Context mContext;
    Cursor cursor;
    int ID;

    StackRemoteViewsFactory(Context context, Intent intent) {
        this.mContext = context;
        ID  =intent.getIntExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
        Log.d("widgett","www");

    }
    @Override
    public void onCreate() {
        if (cursor!=null){
            cursor.close();
        }
        Log.d("widgett","www");

        cursor = mContext.getContentResolver().query(CONTENT_URI,null,null,null,null);
    }

    @Override
    public void onDataSetChanged() {
//        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.darth_vader));
//        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.star_wars_logo));
//        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.storm_trooper));
//        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.starwars));
//        mWidgetItems.add(BitmapFactory.decodeResource(mContext.getResources(), R.drawable.falcon));
    }

    @Override
    public void onDestroy() {

    }

    @Override
    public int getCount() {
        return cursor.getCount();
    }
    private WidgetItem getItems(int position){
        if (!cursor.moveToPosition(position)){
            try {
                throw new IllegalAccessException("Error");
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return new WidgetItem(cursor);
    }

    @Override
    public RemoteViews getViewAt(int i) {
        Log.d("widgett","www");

        RemoteViews rv = new RemoteViews(mContext.getPackageName(), R.layout.widget_item);
        WidgetItem widgetItem = getItems(i);
        Bitmap bitmap = null;
        try {
            bitmap = Glide.with(mContext).asBitmap().load("https://image.tmdb.org/t/p/w185"+widgetItem.getPosterPath()).apply(new RequestOptions().fitCenter()).submit().get();
        }catch (InterruptedException| ExecutionException e  ){
            e.printStackTrace();
        }
        rv.setImageViewBitmap(R.id.imageView,bitmap);

        Bundle extras = new Bundle();
        extras.putInt(ImageBannerWidget.EXTRA_ITEM,i);
        Intent intent = new Intent();
        intent.putExtras(extras);
        rv.setOnClickFillInIntent(R.id.imageView,intent);
        return rv;
    }

    @Override
    public RemoteViews getLoadingView() {
        return null;
    }

    @Override
    public int getViewTypeCount() {
        return 1;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public boolean hasStableIds() {
        return false;
    }
}
