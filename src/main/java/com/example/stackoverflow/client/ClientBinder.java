package com.example.stackoverflow.client;


import com.example.stackoverflow.model.User;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Response;
import org.glassfish.hk2.api.Factory;
import org.glassfish.hk2.utilities.binding.AbstractBinder;
import org.glassfish.jersey.process.internal.RequestScoped;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.io.IOException;
import java.util.Objects;


public class ClientBinder extends AbstractBinder {

    private String baseUrl;


    public ClientBinder(String baseUrl) {
        Objects.requireNonNull(baseUrl, "baseUrl must not be null.");
        this.baseUrl = baseUrl;
    }

    @Override
    public void configure() {
        bindFactory(RetrofitFactory.class)
                .to(Retrofit.class)
                .in(Singleton.class);

        bindFactory(UserFactory.class)
                .to(User.class)
                .in(RequestScoped.class)
                .proxy(true);

        bindFactory(ClientFactories.MessageClientFactory.class)
                .to(MessageClient.class)
                .in(Singleton.class);

        bind(new BaseUrlProvider(this.baseUrl))
                .to(BaseUrlProvider.class);
    }


    private static class RetrofitFactory implements Factory<Retrofit> {

        private final Retrofit retrofit;

        @Inject
        private RetrofitFactory(Provider<User> userProvider, BaseUrlProvider urlProvider) {
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new UserIdInterceptor(userProvider))
                    .build();

            this.retrofit = new Retrofit.Builder()
                    .baseUrl(urlProvider.baseUrl)
                    .addConverterFactory(GsonConverterFactory.create())
                    .client(client)
                    .build();
        }

        @Override
        public Retrofit provide() {
            return this.retrofit;
        }

        @Override
        public void dispose(Retrofit instance) { /* noop */ }
    }


    private static class UserIdInterceptor implements Interceptor {

        private final Provider<User> userProvider;

        UserIdInterceptor(Provider<User> userProvider) {
            this.userProvider = userProvider;
        }

        @Override
        public Response intercept(Chain chain) throws IOException {

            final User user = userProvider.get();
            if (user.isValid()) {
                return chain.proceed(chain.request().newBuilder()
                        .addHeader("User-Id", userProvider.get().getName())
                        .build());
            } else {
                return chain.proceed(chain.request());
            }
        }
    }

    /**
     * Since we are using Factories that need to be instantiated by the DI framework,
     * those factories need to be static. So they will not have access to the
     * baseUri passed into the parent ClientBinder. This is a workaround.
     * We make a wrapper for the baseUri and make it injectable. Components
     * that need it, will just add this class as a dependency.
     */
    private class BaseUrlProvider {
        private final String baseUrl;

        BaseUrlProvider(String baseUrl) {
            this.baseUrl = baseUrl;
        }
    }
}
