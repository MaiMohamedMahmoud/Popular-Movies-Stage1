package com.marscode.pwn.aflamk;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.marscode.pwn.aflamk.Data.ApiUtils;
import com.marscode.pwn.aflamk.Models.Movies;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import static com.marscode.pwn.aflamk.Data.ApiUtils.API_KEY;
import static com.marscode.pwn.aflamk.Data.ApiUtils.Base_image_URl;
import static com.marscode.pwn.aflamk.Data.ApiUtils.image_size;

public class MovieDetailsActivity extends AppCompatActivity {
    int Movies_Id;
    TextView movie_review_txt;
    TextView movie_rate_txt;
    TextView release_date_txt;
    ImageView movie_poster_image;
    Toolbar toolbar;
    Activity activity;
    CollapsingToolbarLayout toolbarLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle extras = getIntent().getExtras();
        Movies_Id = (extras.getInt("Movies_Id"));
        movie_review_txt = findViewById(R.id.movie_review_txt);
        movie_rate_txt = findViewById(R.id.movie_rate_txt);
        release_date_txt = findViewById(R.id.release_date_txt);
        movie_poster_image = findViewById(R.id.movie_poster_image);
        activity = this;
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (isOnline() && isNetworkAvailable(getApplicationContext())) {
            getMovieDetails();
        } else {
            Toast.makeText(getApplicationContext(), R.string.internet_connection, Toast.LENGTH_LONG).show();
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
            int exitValue = ipProcess.waitFor();
            return (exitValue == 0);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return false;
    }


    private void getMovieDetails() {
        ApiUtils.getMovieService().GetMovieDetails(Movies_Id, API_KEY).enqueue(MovieDetails);
    }

    Callback<Movies> MovieDetails = new Callback<Movies>() {
        @Override
        public void onResponse(Call<Movies> call, Response<Movies> response) {
            Movies moviesObj = response.body();
            Log.i("sucess", "Requested URL " + call.request());
            Log.i("Response Body", response.body() + "");

            movie_review_txt.setText(moviesObj.getOverview());

            movie_rate_txt.setText(moviesObj.getVoteAverage().toString() + getString(R.string.rate_txt));
            release_date_txt.setText(moviesObj.getReleaseDate());
            Picasso.with(getApplicationContext()).
                    load(getImageFullPath(moviesObj.getPosterPath())).
                    into(movie_poster_image);
            toolbarLayout.setTitle(moviesObj.getTitle());
        }

        public String getImageFullPath(String posterPath) {
            Log.i("URL", Base_image_URl + image_size + posterPath);
            return Base_image_URl + image_size + posterPath;

        }

        @Override
        public void onFailure(Call<Movies> call, Throwable t) {
            Log.i("fail", "Requested URL " + call.request() + "Throwable Message " + t.getMessage());

        }
    };
}
