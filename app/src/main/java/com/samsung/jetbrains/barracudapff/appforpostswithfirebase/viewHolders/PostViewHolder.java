package com.samsung.jetbrains.barracudapff.appforpostswithfirebase.viewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.R;
import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.models.Post;

public class PostViewHolder extends RecyclerView.ViewHolder {
    public TextView nameView;
    public TextView authorView;
    public TextView likesCountView;
    public TextView textView;

    public ImageView starView;

    public PostViewHolder(@NonNull View itemView) {
        super(itemView);

        nameView = itemView.findViewById(R.id.post_name);
        authorView = itemView.findViewById(R.id.post_author);
        //likesCountView = itemView.findViewById(R.id.post_count);
        textView = itemView.findViewById(R.id.post_text);
        starView = itemView.findViewById(R.id.post_star);
    }

    public void onBind(Post post) {
        textView.setText(post.text);
        authorView.setText(post.author_id);
        //likesCountView.setText(post.user_likes.size());
        nameView.setText(post.name);
    }
}
