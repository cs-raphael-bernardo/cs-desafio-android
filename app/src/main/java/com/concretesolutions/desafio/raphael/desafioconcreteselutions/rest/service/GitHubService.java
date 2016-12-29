package com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.service;

import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.RestCallback;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.GitHubResponse;
import com.google.gson.JsonArray;

import java.util.HashMap;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;
import retrofit.http.QueryMap;

/**
 * Created by Raphael on 11/10/2016.
 */

public interface GitHubService {

    @GET("/search/repositories")
    void getRepositories(@QueryMap HashMap<String, String> params, RestCallback<GitHubResponse> callback);

    @GET("/repos/{criador}/{repositorio}/pulls")
    void getPullRequests(@Path("criador") String criador, @Path("repositorio") String repositorio,
                         Callback<JsonArray> callback);
}
