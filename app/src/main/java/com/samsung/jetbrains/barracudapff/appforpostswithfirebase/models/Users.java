package com.samsung.jetbrains.barracudapff.appforpostswithfirebase.models;

public class Users {
    public String fai213;
    public String key;

    public Users() {
    }

    public Users(String fai213, String key) {
        this.fai213 = fai213;
        this.key = key;
    }

    @Override
    public String toString() {
        return "Users{" +
                "fai213='" + fai213 + '\'' +
                ", key='" + key + '\'' +
                '}';
    }
}
