package com.marscode.pwn.aflamk;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marscode.pwn.aflamk.Models.Movies;
import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.marscode.pwn.aflamk.Data.ApiUtils.Base_image_URl;
import static com.marscode.pwn.aflamk.Data.ApiUtils.image_size;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.ViewHolder> {

    List<Movies> moviesList;
    Context context;

    MovieAdapter(List<Movies> moviesList, Context context) {
        this.moviesList = moviesList;
        this.context = context;
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
        return moviesList.size();
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
                    Intent intent = new Intent(context, MovieDetailsActivity.class);
                    intent.putExtra("Movies_Id", moviesList.get(position).getId());
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            });
        }

        public String getImageFullPath(String posterPath) {
            Log.i("URL", Base_image_URl + image_size + posterPath);
            return Base_image_URl + image_size + posterPath;

        }
    }
}

