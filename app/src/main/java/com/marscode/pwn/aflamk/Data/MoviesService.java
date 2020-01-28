package com.marscode.pwn.aflamk.Data;

import com.marscode.pwn.aflamk.Models.Movies;
import com.marscode.pwn.aflamk.Models.MoviesListResponse;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface MoviesService {

    @GET("movie/popular")
    Call<MoviesListResponse> PopularMovies(@Query("api_key") String apiKei);

    @GET("movie/top_rated")
    Call<MoviesListResponse> TopRatedMovies(@Query("api_key") String apiKei);


    @GET("movie/{movie_id}")
    Call<Movies> GetMovieDetails(@Path("movie_id") int movie_id, @Query("api_key") String apiKei);

}
