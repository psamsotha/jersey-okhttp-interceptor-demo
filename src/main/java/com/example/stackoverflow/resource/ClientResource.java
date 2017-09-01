package com.example.stackoverflow.resource;


import com.example.stackoverflow.client.MessageClient;
import com.example.stackoverflow.model.Message;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("client")
public class ClientResource {

    @Inject
    private MessageClient client;


    @GET
    @Produces("application/json")
    public Message getClientMessage() throws Exception {
        return this.client.getMessage().execute().body();
    }
}
