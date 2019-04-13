package com.samsung.jetbrains.barracudapff.appforpostswithfirebase.adapters;

import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.R;
import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.models.Post;
import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.viewHolders.PostViewHolder;

public class PostsAdapter extends FirebaseRecyclerAdapter<Post, PostViewHolder> {
    public PostsAdapter(@NonNull FirebaseRecyclerOptions<Post> options) {
        super(options);
    }

    //Put data (model) in ViewHolder (layout)
    @Override
    protected void onBindViewHolder(@NonNull PostViewHolder holder, int position, @NonNull Post model) {
        holder.onBind(model);
        System.out.println("Bind!");
        System.out.println(model);
    }

    //Create ViewHolder
    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        System.out.println("onCreateViewHolder!");
        return new PostViewHolder(LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.item_post, viewGroup, false));
    }
}
