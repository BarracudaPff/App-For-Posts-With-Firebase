package com.samsung.jetbrains.barracudapff.appforpostswithfirebase;

import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.adapters.PostsAdapter;
import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.models.Post;

public class MainActivity extends AppCompatActivity {

    protected static final Query sChatQuery =
            FirebaseDatabase.getInstance()
                    .getReference()
                    .child("posts")
                    .limitToLast(50);

    private PostsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.posts_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Post> options = new FirebaseRecyclerOptions.Builder<Post>()
                .setQuery(sChatQuery, Post.class)
                .build();

        adapter = new PostsAdapter(options);
        recyclerView.setAdapter(adapter);

        FloatingActionButton actionButton = findViewById(R.id.floatingActionButton);
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String key = FirebaseDatabase.getInstance()
                        .getReference()
                        .child("posts")
                        .push().getKey();

                Post post = new Post(
                        key,
                        "name",
                        "text",
                        null,
                        0,
                        null
                );

                FirebaseDatabase.getInstance().getReference()
                        .child("posts")
                        .child(key)
                        .setValue(post);
            }
        });
    }
}
