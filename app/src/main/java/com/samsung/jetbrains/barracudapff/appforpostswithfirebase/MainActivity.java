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
                        "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.",
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

    @Override
    protected void onResume() {
        super.onResume();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
