package com.concretesolutions.desafio.raphael.desafioconcreteselutions.helpers;

import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.StoreCallBack;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.StoreCallBackJSONArray;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.StoreCallBackPullList;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.GitHubResponse;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.Items;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.pulls.Pull;
import com.google.gson.JsonArray;
import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Raphael on 11/10/2016.
 */
public class CacheStorage {
    public final static String REPOSITORIES_RESPONSE = "repositories";
    public final static String REPOSITORIES_PULL_REQUESTS = "repositoryPullRequests-";

    public static void storeRepositoriesResponseAsync(GitHubResponse gitHubResponse, StoreCallBack callBack) {
        hawkAsyncStore(REPOSITORIES_RESPONSE, gitHubResponse, callBack);
    }

    /**
     * Async builder
     * @param value
     * @param object
     * @param callBack
     */
    private static void hawkAsyncStore(final String value, final GitHubResponse object,
                                       final StoreCallBack callBack) {
        Thread t1 = new Thread(buildRunnable(value, object, callBack));
        t1.start();
    }

    private static void hawkAsyncStore(final String value, final List<Pull> object,
                                       final StoreCallBackPullList callBack) {
        Thread t1 = new Thread(buildRunnable(value, object, callBack));
        t1.start();
    }

    /**
     * Stores data asynchronously, supports callbacks that implement StoreCallBack,
     * success() of fail() will be called.
     *
     * @param value Used by the methods to store the right key.
     * @param object The data to be stored.
     * @param callBack A callback that is called back if the data was succesfully stored or not,
     *                 must implement GitHubResponse.
     */
    private static Runnable buildRunnable(final String value, final GitHubResponse object,
                                          final StoreCallBack callBack) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    if (Hawk.contains(value))
                        Hawk.delete(value);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (Hawk.put(value, object))
                            if (callBack != null)
                                callBack.success(object);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (callBack != null)
                            callBack.fail(object, e);
                    }
                }
            }
        };
    }

    private static Runnable buildRunnable(final String value, final List<Pull> object,
                                          final StoreCallBackPullList callBack) {
        return new Runnable() {
            @Override
            public void run() {
                try {
                    if (Hawk.contains(value))
                        Hawk.delete(value);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        if (Hawk.put(value, object))
                            if (callBack != null)
                                callBack.success(object);
                    } catch (Exception e) {
                        e.printStackTrace();
                        if (callBack != null)
                            callBack.fail(object, e);
                    }
                }
            }
        };
    }

    public static GitHubResponse getRepositoriesResponse() {
        return Hawk.get(REPOSITORIES_RESPONSE);
    }

    public static List<Pull> getPullRequestsResponse(String login, String repo) {
        return Hawk.get(REPOSITORIES_PULL_REQUESTS + login + "-" + repo);
    }

    private static void storePullRequestsResponseAsync(List<Pull> finalResult, String login, String repo,
                                                       StoreCallBackPullList callback) {
        hawkAsyncStore(REPOSITORIES_PULL_REQUESTS + login + "-" + repo, finalResult, callback);
    }

    public static List<Pull> mergeAndSavePullRequests(List<Pull> oldPullList, List<Pull> pullList, String login, String repo, StoreCallBackPullList callback) {

        List<Pull> finalResult;
        if (oldPullList != null && oldPullList.size() > 0 && pullList != null && pullList.size() > 0) {
            List<Pull> mergedT = new ArrayList<>(oldPullList);
            for (Pull tIncoming : pullList) {
                if (!oldPullList.contains(tIncoming)) {
                    mergedT.add(tIncoming);
                }
            }
            finalResult =  new ArrayList<>(mergedT);
        } else if (pullList == null) {
            finalResult = oldPullList;
        } else {
            finalResult = pullList;
        }

        storePullRequestsResponseAsync(finalResult, login, repo, callback);

        return finalResult;
    }
}
