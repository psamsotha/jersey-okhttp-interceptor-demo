package com.example.stackoverflow.client;

import com.example.stackoverflow.model.Message;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MessageClient {

    @GET("/message")
    Call<Message> getMessage();
}
