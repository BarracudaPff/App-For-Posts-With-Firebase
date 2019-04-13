package com.samsung.jetbrains.barracudapff.appforpostswithfirebase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.adapters.PostsAdapter;
import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.models.Post;

public class MainActivity extends AppCompatActivity {

    protected static final Query sChatQuery =
            FirebaseDatabase.getInstance().getReference().child("chats").limitToLast(50);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerView = findViewById(R.id.posts_list);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Post> options = new FirebaseRecyclerOptions.Builder<Post>()
                .setQuery(sChatQuery, Post.class)
                .setLifecycleOwner(this)
                .build();

        PostsAdapter adapter = new PostsAdapter(options);
    }
}
