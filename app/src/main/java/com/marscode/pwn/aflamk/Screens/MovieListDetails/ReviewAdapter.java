package com.marscode.pwn.aflamk.Screens.MovieListDetails;

import android.content.Context;
import android.content.res.Resources;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.marscode.pwn.aflamk.Models.Reviews;
import com.marscode.pwn.aflamk.R;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ReviewAdapter extends RecyclerView.Adapter<ReviewAdapter.ViewHolder> {

    List<Reviews> reviewList;
    Context context;
    private static final String YOUTUBE_TRAILER_TEMPLATE = "http://img.youtube.com/vi/%s/mqdefault.jpg";
    private static final String YOUTUBE_VIDE_TEMPLATE = "https://www.youtube.com/watch?v=";

    public ReviewAdapter(Context context, List<Reviews> reviewList) {
        this.context = context;
        this.reviewList = reviewList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.review_list_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(position);
    }

    @Override
    public int getItemCount() {
        return reviewList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView review_description;
        TextView review_author;
        TextView review_link;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            review_description = itemView.findViewById(R.id.review_description);
            review_author = itemView.findViewById(R.id.review_author);
            review_link = itemView.findViewById(R.id.review_link);
        }

        public void bind(final int position) {

            review_description.setText(reviewList.get(position).getContent());
            review_author.setText(reviewList.get(position).getAuthor());
            Resources res = context.getResources();
            String dynamicUrl = reviewList.get(position).getUrl(); // or whatever you want, it's dynamic

            String linkedText =
                    String.format("<a href=\"%s\">link</a> ", dynamicUrl) ;

            review_link.setText(Html.fromHtml(linkedText));
            review_link.setMovementMethod(LinkMovementMethod.getInstance());


        }
    }


}

