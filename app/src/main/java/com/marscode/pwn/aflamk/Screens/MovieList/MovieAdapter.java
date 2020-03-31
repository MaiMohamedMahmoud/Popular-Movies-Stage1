package com.marscode.pwn.aflamk.Screens.MovieList;

import android.app.Activity;
import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marscode.pwn.aflamk.Data.MovieListner;
import com.marscode.pwn.aflamk.Models.Movies;
import com.marscode.pwn.aflamk.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.marscode.pwn.aflamk.Data.ApiUtils.Base_image_URl;
import static com.marscode.pwn.aflamk.Data.ApiUtils.image_size;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    List<Movies> moviesList;
    Context context;
    MovieListner movieListner;

    public MovieAdapter(Context context , MovieListner movieListner) {
        this.context = context;
        this.movieListner = movieListner;
    }

    public void setMoviesList(List<Movies> movies) {
        moviesList = movies;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(holder, position);
    }


    @Override
    public int getItemCount() {
        if (moviesList == null) {
            return 0;
        } else {

            return moviesList.size();
        }
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView movie_image;
        TextView txt;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            movie_image = itemView.findViewById(R.id.movie_image);

        }


        public void bind(ViewHolder holder, final int position) {
            Picasso.with((context)).
                    load(getImageFullPath(moviesList.get(position).getPosterPath())).
                    into(movie_image);

            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    movieListner.onClickItem(context, movie_image, moviesList.get(position));
                }
            });
        }

        public String getImageFullPath(String posterPath) {
            Log.i("URL", Base_image_URl + image_size + posterPath);
            return Base_image_URl + image_size + posterPath;

        }
    }
}