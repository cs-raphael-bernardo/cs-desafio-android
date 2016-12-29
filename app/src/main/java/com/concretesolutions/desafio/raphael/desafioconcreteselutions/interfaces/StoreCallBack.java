package com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces;

import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.GitHubResponse;

/**
 * Created by Raphael on 11/10/2016.
 */
public interface StoreCallBack {
    void success(GitHubResponse prapagarResponse);
    void fail(GitHubResponse prapagarResponse, Exception e);
}
