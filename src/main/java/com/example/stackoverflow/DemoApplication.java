package com.example.stackoverflow;

import com.example.stackoverflow.client.ClientBinder;
import com.example.stackoverflow.resource.ClientResource;
import com.example.stackoverflow.resource.MessageResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;


public class DemoApplication extends Application<DemoConfiguration> {

    public static void main(String... args) throws Exception {
        new DemoApplication().run(args);
    }

    @Override
    public void run(DemoConfiguration conf, Environment env) throws Exception {
        env.jersey().getResourceConfig()
                .register(ClientResource.class)
                .register(MessageResource.class)
                .register(new ClientBinder(conf.getRetrofitBaseUrl()));
    }
}
