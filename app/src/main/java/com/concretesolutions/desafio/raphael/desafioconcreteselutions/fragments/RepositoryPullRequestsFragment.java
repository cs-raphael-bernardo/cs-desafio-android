package com.concretesolutions.desafio.raphael.desafioconcreteselutions.fragments;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.concretesolutions.desafio.raphael.desafioconcreteselutions.R;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.adapters.PullRequestsAdapter;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.helpers.CacheStorage;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.OnFragmentActionListener;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.OnFragmentHttpResponse;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.StoreCallBackPullList;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.RestClient;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.pulls.Pull;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.service.GitHubService;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import cn.pedant.SweetAlert.SweetAlertDialog;
import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentActionListener} interface
 * to handle interaction events.
 * Use the {@link RepositoryPullRequestsFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepositoryPullRequestsFragment extends Fragment {
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param12";
    public static final String TAG = "RepositoryPullRequestsFragment";
    private List<Pull> pullList;

    // TODO: Rename and change types of parameters
    private String serializedList;

    private OnFragmentActionListener mListener;
    private RecyclerView mRecyclerView;
    private String login;
    private String repo;

    public RepositoryPullRequestsFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment RepositoryPullRequestsFragment.
     */
    public static RepositoryPullRequestsFragment newInstance(String param1, String param2) {
        RepositoryPullRequestsFragment fragment = new RepositoryPullRequestsFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            login = getArguments().getString(ARG_PARAM1);
            repo = getArguments().getString(ARG_PARAM2);
        }

        // retain this fragment
        setRetainInstance(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View mRootView = inflater.inflate(R.layout.fragment_repository_details, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.recycle_view);

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle(repo);

        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentActionListener) {
            mListener = (OnFragmentActionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentActionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResume() {
        super.onResume();

        List<Pull> cachedList = CacheStorage.getPullRequestsResponse(login, repo);
        if (cachedList != null) {
            pullList = cachedList;
            writeScreenData();
            queryServerForData(login, repo, true);
        } else {
            queryServerForData(login, repo, false);
        }
    }

    private void writeScreenData() {
        // use a linear layout manager
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(mLayoutManager);

        // specify an adapter (see also next example)
        PullRequestsAdapter adapter = new PullRequestsAdapter(mListener, pullList);
        mRecyclerView.setAdapter(adapter);
    }

    private void queryServerForData(final String login, final String repo, final boolean background) {
        GitHubService gh = RestClient.getRestAdapter().create(GitHubService.class);
        String loading = "Listando " + repo + " em background...";
        String loading2 = "Listando " + repo + "...";

        if (!background) {
            mListener.showProgressDialog(loading2);
        } else {
            Toast.makeText(getContext(), loading, Toast.LENGTH_SHORT).show();
        }
        gh.getPullRequests(login, repo, new Callback<JsonArray>() {
            @Override
            public void failure(RetrofitError error) {
                if (!background)
                    mListener.dismissProgressDialog();
                mListener.showRestErrorMessage(error);
            }

            @Override
            public void success(JsonArray jsonArray, Response response) {
                if (!background)
                    mListener.dismissProgressDialog();

                // Copia a lista antes de atualiza-la
                final List<Pull> oldPullList = pullList != null ? new ArrayList<>(pullList) : new ArrayList<Pull>();

                // Extrair dados do JSON
                pullList = processResponse(jsonArray);

                if (pullList.size() > 0) {
                    // Cachear resultado
                    pullList = CacheStorage.mergeAndSavePullRequests(oldPullList, pullList, login, repo, new StoreCallBackPullList() {
                        @Override
                        public void success(List<Pull> object) {
                            try {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), pullList.size() - oldPullList.size() + " itens gravados em cache! "
                                                + pullList.size() + " itens na lista.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }

                        @Override
                        public void fail(List<Pull> object, Exception e) {
                            try {
                                getActivity().runOnUiThread(new Runnable() {
                                    @Override
                                    public void run() {
                                        Toast.makeText(getActivity(), "Dados não foram gravados.", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } catch (Exception e2) {
                                e.printStackTrace();
                                e2.printStackTrace();
                            }
                        }
                    });
                }

                if (pullList != null && pullList.size() > 0) {
                    writeScreenData();
                } else {
                    SweetAlertDialog dialog = new SweetAlertDialog(getContext(), SweetAlertDialog.WARNING_TYPE);
                    dialog.setTitleText("Informação");
                    dialog.setContentText("Repositório " + repo + " não contém Pull Requests.");
                    dialog.setOnDismissListener(new DialogInterface.OnDismissListener() {
                        @Override
                        public void onDismiss(DialogInterface dialogInterface) {
                            getActivity().onBackPressed();
                        }
                    });
                    dialog.show();
                }
            }
        });
    }

    private List<Pull> processResponse(JsonArray jsonArray) {
        Gson gson = new Gson();
        List<Pull> pullList = new ArrayList<>();
        for (JsonElement obj : jsonArray) {
            pullList.add(gson.fromJson(obj, Pull.class));
        }
        return pullList;
    }
}
