package com.marscode.pwn.aflamk;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.marscode.pwn.aflamk.Data.ApiUtils;
import com.marscode.pwn.aflamk.Data.MoviesService;
import com.marscode.pwn.aflamk.Models.Movies;
import com.marscode.pwn.aflamk.Models.MoviesListResponse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static com.marscode.pwn.aflamk.Data.ApiUtils.API_KEY;

public class MainActivity extends AppCompatActivity {

    private MoviesService moviesApi;
    RecyclerView movieListRecycle;
    List<Movies> moviesList;
    List<Movies> topMoviesList;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        moviesApi = ApiUtils.getMovieService();

        moviesList = new ArrayList<>();
        topMoviesList = new ArrayList<>();
        movieListRecycle = findViewById(R.id.movie_list);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        movieListRecycle.setLayoutManager(gridLayoutManager);
        context = getApplicationContext();

        if(isOnline()&& isNetworkAvailable(context)){
            getPopularMovies(API_KEY);
            setTitle(R.string.popular);
        }
        else
        {
            Toast.makeText(context,R.string.internet_connection,Toast.LENGTH_LONG).show();
        }

    }

    public boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = ((ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE));
        return connectivityManager.getActiveNetworkInfo() != null && connectivityManager.getActiveNetworkInfo().isConnected();
    }

    public boolean isOnline() {
        Runtime runtime = Runtime.getRuntime();
        try {
            Process ipProcess = runtime.exec("/system/bin/ping -c 1 8.8.8.8");
            int     exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        }
        catch (IOException e)          { e.printStackTrace(); }
        catch (InterruptedException e) { e.printStackTrace(); }

        return false;
    }

    public void getPopularMovies(String ApiKey) {

        moviesApi.PopularMovies(ApiKey).enqueue(MovieResponseCallback);

    }

    public void getTopRatedMovies(String ApiKey) {
        moviesApi.TopRatedMovies(ApiKey).enqueue(MovieResponseCallback);
    }

    Callback<MoviesListResponse> MovieResponseCallback = new Callback<MoviesListResponse>() {
        @Override
        public void onResponse(Call<MoviesListResponse> call, Response<MoviesListResponse> response) {

            MoviesListResponse moviesListResponse = response.body();
            Log.i("Response Body", response.body().getPage().toString() + "");
            moviesList.addAll(moviesListResponse.getResults());
            movieListRecycle.setAdapter(new MovieAdapter(moviesList, context));

        }

        @Override
        public void onFailure(Call<MoviesListResponse> call, Throwable t) {
            Log.i("fail", "Requested URL " + call.request() + "Throwable Message " + t.getMessage());

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.movies_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.movie_top) {
            moviesList.clear();
            getTopRatedMovies(API_KEY);
            setTitle(R.string.top_rated);
        }
        if (id == R.id.movie_popular) {
            moviesList.clear();
            getPopularMovies(API_KEY);
            setTitle(R.string.popular);
        }
        return super.onOptionsItemSelected(item);
    }
}

