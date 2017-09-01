package com.example.stackoverflow;

import io.dropwizard.Configuration;

public class DemoConfiguration extends Configuration {

    private String retrofitBaseUrl = "http://localhost:8080";


    public String getRetrofitBaseUrl() {
        return this.retrofitBaseUrl;
    }

    public void setRetrofitBaseUrl(String retrofitBaseUrl) {
        this.retrofitBaseUrl = retrofitBaseUrl;
    }
}
