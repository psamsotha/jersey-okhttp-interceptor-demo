package com.example.stackoverflow.client;

import org.glassfish.hk2.api.Factory;
import retrofit2.Retrofit;

import javax.inject.Inject;

public class ClientFactories {


    public static class MessageClientFactory implements Factory<MessageClient> {

        @Inject
        private Retrofit retrofit;

        @Override
        public MessageClient provide() {
            return this.retrofit.create(MessageClient.class);
        }

        @Override
        public void dispose(MessageClient instance) { /* noop */ }
    }
}
