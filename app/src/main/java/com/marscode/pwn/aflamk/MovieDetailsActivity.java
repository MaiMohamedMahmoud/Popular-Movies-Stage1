package com.marscode.pwn.aflamk;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.os.Bundle;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.marscode.pwn.aflamk.Data.ApiUtils;
import com.marscode.pwn.aflamk.Models.Movies;
import com.marscode.pwn.aflamk.Models.MoviesListResponse;
import com.marscode.pwn.aflamk.Models.ReviewResponse;
import com.marscode.pwn.aflamk.Models.Reviews;
import com.marscode.pwn.aflamk.Models.VideoResponse;
import com.marscode.pwn.aflamk.Models.Videos;
import com.squareup.picasso.Picasso;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
    List<Videos> video_list;
    List<Reviews> review_list;
    RecyclerView video_recycle_View;
    RecyclerView review_recycle_View;
    Context context;
    Movies movie;
    private MovieViewModel mMovieViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbarLayout = findViewById(R.id.toolbar_layout);
        video_list = new ArrayList<>();
        review_list = new ArrayList<>();
        context = this;
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        Bundle extras = getIntent().getExtras();
        Movies_Id = (extras.getInt("Movies_Id"));
        movie_review_txt = findViewById(R.id.movie_review_txt);
        movie_rate_txt = findViewById(R.id.movie_rate_txt);
        release_date_txt = findViewById(R.id.release_date_txt);
        movie_poster_image = findViewById(R.id.movie_poster_image);
        video_recycle_View = findViewById(R.id.video_list_recycle_view);
        review_recycle_View = findViewById(R.id.review_list_recycle_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getApplicationContext(), RecyclerView.HORIZONTAL, false);
        video_recycle_View.setLayoutManager(linearLayoutManager);
        LinearLayoutManager linearLayoutManagerReview = new LinearLayoutManager(getApplicationContext(), RecyclerView.VERTICAL, false);
        review_recycle_View.setLayoutManager(linearLayoutManagerReview);
        activity = this;
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();

                mMovieViewModel = new MovieViewModel(getApplication());
                Log.d("sss",movie+"");
                mMovieViewModel.insert(movie);
            }
        });
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        if (isOnline() && isNetworkAvailable(getApplicationContext())) {
            getMovieDetails();
            getVideos();
            getReviews();
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
            movie = moviesObj;
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

    private void getVideos() {
        ApiUtils.getMovieService().GetTrailers(Movies_Id, API_KEY).enqueue(MoviesTrailers);
    }

    Callback<VideoResponse> MoviesTrailers = new Callback<VideoResponse>() {
        @Override
        public void onResponse(Call<VideoResponse> call, Response<VideoResponse> response) {
            VideoResponse moviesListResponse = response.body();
            video_list.addAll(moviesListResponse.getVideos());

            video_recycle_View.setAdapter(new VideoAdapter(context, video_list));
        }

        @Override
        public void onFailure(Call<VideoResponse> call, Throwable t) {
            Log.i("fail", "Requested URL  " + call.request() + "Throwable Message " + t.getMessage());

        }
    };

    private void getReviews() {
        ApiUtils.getMovieService().GetReviews(Movies_Id, API_KEY).enqueue(MoviesReviews);
    }

    Callback<ReviewResponse> MoviesReviews = new Callback<ReviewResponse>() {
        @Override
        public void onResponse(Call<ReviewResponse> call, Response<ReviewResponse> response) {
            ReviewResponse reviewListResponse = response.body();
            review_list.addAll(reviewListResponse.getReviews());

            review_recycle_View.setAdapter(new ReviewAdapter(context, review_list));
        }

        @Override
        public void onFailure(Call<ReviewResponse> call, Throwable t) {
            Log.i("fail", "Requested URL  " + call.request() + "Throwable Message " + t.getMessage());

        }
    };
}
