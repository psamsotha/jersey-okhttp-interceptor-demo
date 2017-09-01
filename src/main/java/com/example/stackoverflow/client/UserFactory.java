package com.example.stackoverflow.client;


import com.example.stackoverflow.model.User;
import org.glassfish.hk2.api.Factory;
import org.glassfish.jersey.server.ContainerRequest;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.ws.rs.core.Cookie;


public class UserFactory implements Factory<User> {

    @Inject
    private Provider<ContainerRequest> request;

    @Override
    public User provide() {
        Cookie cookie = request.get().getCookies().get("User-Id");
        return cookie != null
                ? new User(cookie.getValue())
                : new User(null);
    }

    @Override
    public void dispose(User user) { /* noop */ }
}
