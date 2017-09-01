
## Example of using Retrofit and OkHttp client with Jersey and using an Interceptor with Jersey/Dropwizard Context Details

See Stack Overflow question [How can I make OkHttp get a parameter out of Jersey and use in Interceptor?](https://stackoverflow.com/q/45971156/2587435)


### Build

```
./gradlew build
```

### Run

```
./gradlew run
```

### Endpionts

```
http://localhost:8080/client
http://localhost:8080/message
```

### cURL

```
curl http://localhost:8080/client -H 'Cookie: User-Id=Peeskillet'
```

The entry point for testing is the `client` endpoint. That resource will use the `MessageClient` to call the `message`
endpoint, using the Retrofit and OkHttp under the hood. The request the `client` endpoint should have a cookie with
the `User-Id`. Transparently, the Cookie from the initial request will get transferred to the `message` endpoint
call, without the client needing to know anything about the cookie.

