package com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest;

/**
 * Created by Raphael on 11/10/2016.
 */

import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.GitHubResponse;

import retrofit.Callback;
import retrofit.RetrofitError;

/**
 * RestCallback
 *
 * This class was designed to replace the default callback class with the intention
 * of providing a consistent way of handling errors for all requests made using Retrofit.
 *
 * 4/24/2015
 */
public abstract class RestCallback<T> implements Callback<GitHubResponse> {
    public abstract void failure(RetrofitError error);
}
