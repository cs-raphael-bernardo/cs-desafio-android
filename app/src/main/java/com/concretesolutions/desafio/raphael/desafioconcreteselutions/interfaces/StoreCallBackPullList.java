package com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces;

import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.pulls.Pull;

import java.util.List;

/**
 * Created by Raphael on 11/11/2016.
 */
public interface StoreCallBackPullList {
    void success(List<Pull> object);

    void fail(List<Pull> object, Exception e);
}
