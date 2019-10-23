package com.qoolqas.moviecataloguesqlfinal.api;

import com.qoolqas.moviecataloguesqlfinal.data.Movie;
import com.qoolqas.moviecataloguesqlfinal.data.TvShow;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface Service {
    @GET("movie")
    Call<Movie> getMovie(@Query("api_key") String apiKey);

    @GET("tv")
    Call<TvShow> getShow(@Query("api_key") String apiKey);
}
