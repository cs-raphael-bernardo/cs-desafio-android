package com.concretesolutions.desafio.raphael.desafioconcreteselutions.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.concretesolutions.desafio.raphael.desafioconcreteselutions.R;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.adapters.ItemsRecyclerAdapder;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.fragments.util.SimpleDividerItemDecoration;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.helpers.CacheStorage;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.OnFragmentActionListener;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.OnLoadMoreListener;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.StoreCallBack;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.RestCallback;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.RestClient;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.FakeItem;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.GitHubResponse;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.Items;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.service.GitHubService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentActionListener} interface
 * to handle interaction events.
 * Use the {@link RepositoriesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class RepositoriesFragment extends Fragment {
    public static final String TAG = "RepositoriesFragment";

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    private static final int API_RESPONSE_PAGE_SIZE = 30;

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentActionListener mListener;
    private List<Items> mItems;
    private RecyclerView mRecyclerView;
    private int itemCount;

    public RepositoriesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment RepositoriesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static RepositoriesFragment newInstance(String param1, String param2) {
        RepositoriesFragment fragment = new RepositoriesFragment();
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
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View mRootView = inflater.inflate(R.layout.fragment_repositories, container, false);
        mRecyclerView = (RecyclerView) mRootView.findViewById(R.id.repo_recycler);

        // Inflate the layout for this fragment
        return mRootView;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentActionListener) {
            mListener = (OnFragmentActionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
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

        ((AppCompatActivity)getActivity()).getSupportActionBar().setTitle("Github JavaPop");

        loadCachedResponse();
    }

    private void loadCachedResponse() {
        GitHubResponse cachedResponse = CacheStorage.getRepositoriesResponse();

        if (cachedResponse != null) {
            processResponse(cachedResponse, false);    // Cache hit!
            itemCount = cachedResponse.getItems().size();
//            queryServerForData(1, true);      // Puxar dados
        } else
            queryServerForData(1, false);       // Cache miss.
    }

    private void processResponse(final GitHubResponse response, boolean savedata) {

        // Fusão do que chega nesta função do que está na memória volátil
        mItems = mergeItems(mItems, response.getItems());

        // Atualiza a lista de items em memória volátil
        response.setItems(mItems);

        // Salva a lista em memória não volátil
        if (savedata) {
            CacheStorage.storeRepositoriesResponseAsync(response, new StoreCallBack() {
                @Override
                public void success(GitHubResponse prapagarResponse) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), mItems.size() - itemCount + " itens gravados em cache! "
                                    + mItems.size() + " itens na lista.", Toast.LENGTH_SHORT).show();
                            itemCount = mItems.size();
                        }
                    });
                }

                @Override
                public void fail(GitHubResponse prapagarResponse, Exception e) {
                    getActivity().runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getActivity(), "Dados não foram gravados.", Toast.LENGTH_SHORT).show();
                            itemCount = mItems.size();
                        }
                    });
                }
            });
        }

        loadRecyclerItems(response);
    }

    private void queryServerForData(final int mPage, final boolean background) {
        HashMap<String, String> queryMap = new HashMap<>();
        queryMap.put("q", "language:Java");
        queryMap.put("sort", "stars");
        queryMap.put("page", String.valueOf(mPage));

        GitHubService gh = RestClient.getRestAdapter().create(GitHubService.class);

        if (!background)
            mListener.showProgressDialog();
        else {
            String loading = mPage != 0 ? "Carregando página " + mPage + " em background..." : "Carregando em background...";
            Toast.makeText(getContext(), loading, Toast.LENGTH_SHORT).show();
        }

        gh.getRepositories(queryMap, new RestCallback<GitHubResponse>() {
            @Override
            public void failure(RetrofitError error) {
                if (!background)
                    mListener.dismissProgressDialog();
                mListener.showRestErrorMessage(error);
            }

            @Override
            public void success(GitHubResponse gitHubResponse, Response response) {
                if (!background)
                    mListener.dismissProgressDialog();

                // True to save data on cache
                processResponse(gitHubResponse, true);
            }
        });
    }

    /**
     * A LeftJoin for the items given, the incoming first,
     * and the target list second. Will add just the different transactions
     * to the target list and return the merged results.
     *
     * @param incomingItems The new list to be merged.
     * @param localItems The target list.
     * @return List<Items>
     */
    private List<Items> mergeItems(List<Items> localItems, List<Items> incomingItems) {
        if (localItems != null && localItems.size() != 0 && incomingItems != null) {
            List<Items> mergedT = new ArrayList<>(localItems);
            for (Items tIncoming : incomingItems) {
                if (!localItems.contains(tIncoming)) {
                    mergedT.add(tIncoming);
                }
            }
//            Collections.sort(mergedT);
            return mergedT;
        } else if (incomingItems == null) {
//            if (localItems != null) {
//                Collections.sort(localItems);
//            }
            return localItems;
        } else {
//            if (incomingItems != null) {
//                Collections.sort(incomingItems);
//            }
            return incomingItems;
        }
    }

    private void loadRecyclerItems(GitHubResponse response) {
        final LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        List<Items> items = response.getItems();

        try {
            // Cria um item fantasma para dizer que a lista continua
            List<Items> itemsCopia = new ArrayList<>(items);
            if (temProximaPagina(response)) {
                itemsCopia.add(new FakeItem());
            }

            ItemsRecyclerAdapder adapterItems = new ItemsRecyclerAdapder(itemsCopia, (AppCompatActivity) getActivity(),
                    new OnLoadMoreListener() {
                @Override
                public void loadMore() {
                    queryServerForData(mItems.size() / API_RESPONSE_PAGE_SIZE + 1, true);
                }
            });

            mRecyclerView.setAdapter(adapterItems);
            mRecyclerView.setHasFixedSize(false);
            mRecyclerView.setLayoutManager(layoutManager);
            mRecyclerView.addItemDecoration(new SimpleDividerItemDecoration(getResources()));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean temProximaPagina(GitHubResponse response) {
        return mItems.size() < response.getTotal_count();
    }
}
