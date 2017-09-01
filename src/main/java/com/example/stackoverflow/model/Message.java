package com.example.stackoverflow.model;

public class Message {

    private String greeting;

    public Message() {}

    public Message(String user) {
        this.greeting = "Hello " + user + "!";
    }

    public void setGreting(String greeting) {
        this.greeting = greeting;
    }

    public String getGreeting() {
        return this.greeting;
    }
}
