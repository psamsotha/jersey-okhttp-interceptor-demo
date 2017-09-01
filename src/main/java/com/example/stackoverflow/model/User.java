package com.example.stackoverflow.model;

public class User {

    private String name;

    /* Needed to proxy */
    public User() {}

    public User(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public boolean isValid() {
        return this.name != null;
    }
}
