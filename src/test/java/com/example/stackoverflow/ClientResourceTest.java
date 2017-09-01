package com.example.stackoverflow;

import com.example.stackoverflow.client.ClientBinder;
import com.example.stackoverflow.model.Message;
import com.example.stackoverflow.resource.ClientResource;
import com.example.stackoverflow.resource.MessageResource;
import io.dropwizard.testing.junit.ResourceTestRule;
import org.glassfish.jersey.test.grizzly.GrizzlyWebTestContainerFactory;
import org.junit.ClassRule;
import org.junit.Test;

import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.Response;

import static org.assertj.core.api.Assertions.assertThat;

public class ClientResourceTest {

    @ClassRule
    public static ResourceTestRule RULE = ResourceTestRule.builder()
                                         // default grizzly port
            .addProvider(new ClientBinder("http://localhost:9998"))
            .addResource(new ClientResource())
            .addResource(new MessageResource())
            .setTestContainerFactory(new GrizzlyWebTestContainerFactory())
            .build();


    @Test
    public void getMessageWithCookie() {
        final Response response = RULE.target("client")
                .request()
                .cookie(new Cookie("User-Id", "Peeskillet"))
                .get();
        assertThat(response.getStatus()).isEqualTo(200);

        final Message message = response.readEntity(Message.class);
        assertThat(message.getGreeting()).isEqualTo("Hello Peeskillet!");
    }

    @Test
    public void getMessageWithNoCookie() {
        final Response response = RULE.target("client")
                .request()
                .get();

        final Message message = response.readEntity(Message.class);
        assertThat(message.getGreeting()).isEqualTo("Hello Default!");
    }
}
