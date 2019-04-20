package com.samsung.jetbrains.barracudapff.appforpostswithfirebase;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.adapters.PostsAdapter;
import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.models.Post;
import com.samsung.jetbrains.barracudapff.appforpostswithfirebase.models.Users;

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
        System.out.println("STARTED");

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


        final TextView view = findViewById(R.id.textView);

        //ONLY 1 TIME
        FirebaseDatabase.getInstance().getReference()
                .child("users")
                .addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        System.out.println("\n\naddListenerForSingleValueEvent");
                        Users users = dataSnapshot.getValue(Users.class);
                        System.out.println(users);
                        view.setText(users.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        //EVERY TIME WHEN BD CHANGE
        FirebaseDatabase.getInstance().getReference()
                .child("users")
                .addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        System.out.println("\n\naddValueEventListener");
                        Users users = dataSnapshot.getValue(Users.class);
                        System.out.println(users);
                        view.setText(users.toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }
                });
        //ALL TYPES OF DATA CHANGE
        FirebaseDatabase.getInstance().getReference()
                .child("users")
                .addChildEventListener(new ChildEventListener() {
                    @Override
                    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

                    }

                    @Override
                    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

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
