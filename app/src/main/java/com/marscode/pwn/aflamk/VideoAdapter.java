package com.marscode.pwn.aflamk;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.media.Image;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.marscode.pwn.aflamk.Models.Movies;
import com.marscode.pwn.aflamk.Models.Videos;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Locale;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import static com.marscode.pwn.aflamk.Data.ApiUtils.Base_image_URl;
import static com.marscode.pwn.aflamk.Data.ApiUtils.image_size;

public class VideoAdapter extends RecyclerView.Adapter<VideoAdapter.ViewHolder> {

    List<Videos> videosList;
    Context context;
    private static final String YOUTUBE_TRAILER_TEMPLATE = "http://img.youtube.com/vi/%s/mqdefault.jpg";
    private static final String YOUTUBE_VIDE_TEMPLATE = "https://www.youtube.com/watch?v=";

    public VideoAdapter(Context context, List<Videos> videosList) {
        this.context = context;
        this.videosList = videosList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.video_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return videosList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView video_image;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            video_image = itemView.findViewById(R.id.movie_video_image);
        }

        public void bind(final int position) {
            Picasso.with((context)).
                    load(getImageFullPath(videosList.get(position).getKey())).
                    into(video_image);

            video_image.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri url_video = Uri.parse(YOUTUBE_VIDE_TEMPLATE + videosList.get(position).getKey());
                    Intent intent = new Intent(Intent.ACTION_VIEW);
                    intent.setData(url_video);
                    // Start an activity if it's safe
                    if (intent.resolveActivity(context.getPackageManager())!= null) {
                        context.startActivity(intent);
                    }


                }
            });


        }
    }

    public String getImageFullPath(String posterkey) {
        Log.i("URL", String.format(Locale.getDefault(), YOUTUBE_TRAILER_TEMPLATE, posterkey));
        return String.format(Locale.getDefault(), YOUTUBE_TRAILER_TEMPLATE, posterkey);

    }

}

