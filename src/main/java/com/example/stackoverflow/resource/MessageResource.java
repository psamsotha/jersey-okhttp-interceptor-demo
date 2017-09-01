package com.example.stackoverflow.resource;


import com.example.stackoverflow.model.Message;

import javax.ws.rs.*;

@Path("message")
public class MessageResource {

    @GET
    @Produces("application/json")
    public Message getMessage(@HeaderParam("User-Id") @DefaultValue("Default") String name) {
        return new Message(name);
    }
}
