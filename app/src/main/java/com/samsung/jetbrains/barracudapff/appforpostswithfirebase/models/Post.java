package com.samsung.jetbrains.barracudapff.appforpostswithfirebase.models;

import java.util.ArrayList;

public class Post {
    public String key;

    public String name;
    public String text;
    public String author_id;
    public int likes;
    public ArrayList<String> user_likes;

    public Post(String key, String name, String text, String author_id, int likes, ArrayList<String> user_likes) {
        this.key = key;
        this.name = name;
        this.text = text;
        this.author_id = author_id;
        this.likes = likes;
        this.user_likes = user_likes;
    }
}
