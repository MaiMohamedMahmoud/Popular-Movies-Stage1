package com.marscode.pwn.aflamk.Data;


public class ApiUtils {
    public static final String BASE_URL = "http://api.themoviedb.org/3/";
    public final static String API_KEY ="9684e8c38d4fdd3e869d83a6df191baa" /** ENTER YOUR API KEY**/;
    public final static String Base_image_URl = "http://image.tmdb.org/t/p/";
    public final static String image_size = "w500";

    public static MoviesService getMovieService() {
        return RetrofitClient.getClient(BASE_URL).create(MoviesService.class);
    }
}
