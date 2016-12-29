package com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest;

import com.google.gson.FieldNamingPolicy;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.okhttp.OkHttpClient;

import java.util.concurrent.TimeUnit;

import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.GsonConverter;

/**
 * Created by Raphael on 11/10/2016.
 */

public class RestClient {
    private static RestAdapter mRestAdapter;

    public static RestAdapter getRestAdapter() {
        if (mRestAdapter == null) {
            Gson gson = new GsonBuilder()
                    .setFieldNamingPolicy(FieldNamingPolicy.LOWER_CASE_WITH_UNDERSCORES)
                    .setDateFormat("yyyy-MM-dd HH:mm:ss")
                    .create();

            /**
             * Creates OkHTTPClient which is necessary to set the timeout limit for network
             * requests. The reason for needing to set this, is that some requests, such as
             * registration, will exceed the default timeout limit even when the server/network
             * is operating normally.
             */
            OkHttpClient okHttpClient = new OkHttpClient();
            okHttpClient.setConnectTimeout(15, TimeUnit.SECONDS);
            okHttpClient.setReadTimeout(1, TimeUnit.MINUTES);

            /**
             * This logic ensures that if the app is signed with the release key, the api requests
             * should use prapagar.com, as opposed to staging.prapagar.com, and also turns off
             * automatic logging of network requests via retrofit.
             */
            mRestAdapter = new RestAdapter.Builder()
                    .setLogLevel(RestAdapter.LogLevel.FULL)
                    .setEndpoint("https://api.github.com")
                    .setClient(new OkClient(okHttpClient))
                    .setConverter(new GsonConverter(gson))
                    .build();

        }

        return mRestAdapter;
    }
}
