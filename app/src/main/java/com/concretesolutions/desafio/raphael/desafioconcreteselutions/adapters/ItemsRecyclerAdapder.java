package com.concretesolutions.desafio.raphael.desafioconcreteselutions.adapters;

import android.graphics.Typeface;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.concretesolutions.desafio.raphael.desafioconcreteselutions.R;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.fragments.RepositoryPullRequestsFragment;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.OnFragmentActionListener;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.OnLoadMoreListener;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.FakeItem;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.Items;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by Raphael on 11/10/2016.
 */
public class ItemsRecyclerAdapder extends  RecyclerView.Adapter<ItemsRecyclerAdapder.ViewHolder> {
    private final List<Items> mItems;
    private final AppCompatActivity activity;
    private final Typeface typeFace;
    private final OnLoadMoreListener mLoadMoreCallback;
    private final OnFragmentActionListener mFragmentActionCallback;

    public ItemsRecyclerAdapder(List<Items> itemsCopia, AppCompatActivity activity,
                                OnLoadMoreListener mLoadMoreCallback) {
        this.mItems = itemsCopia;
        this.activity = activity;
        this.mLoadMoreCallback = mLoadMoreCallback;
        this.mFragmentActionCallback = (OnFragmentActionListener) activity;

        typeFace = Typeface.createFromAsset(activity.getAssets(),"fonts/fontawesome-webfont.ttf");
    }

    @Override
    public ItemsRecyclerAdapder.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.list_item_repository, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ItemsRecyclerAdapder.ViewHolder holder, int position) {
        try {
            if (mItems.get(position) != null && !isItemValid(mItems.get(position))) {
                    holder.getmLoadMore().setVisibility(TextView.VISIBLE);
                    holder.getmBlock1().setVisibility(LinearLayout.GONE);
                    holder.getmBlock2().setVisibility(LinearLayout.GONE);
                    holder.getmLoadMore().setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            mLoadMoreCallback.loadMore();
                        }
                    });
            } else {
                // Item válido
                holder.getmLoadMore().setVisibility(TextView.GONE);
                holder.getmBlock1().setVisibility(LinearLayout.VISIBLE);
                holder.getmBlock2().setVisibility(LinearLayout.VISIBLE);
                writeData(holder, position);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private boolean isItemValid(Items items) {
        if (!(items instanceof FakeItem) &&
            items.getDescription() != null &&
            items.getForks_count() != null &&
            items.getStargazers_count() != null &&
            items.getOwner().getLogin() != null &&
            items.getFull_name() != null)
            return true;
        else return false;
    }

    private void writeData(ItemsRecyclerAdapder.ViewHolder holder, final int position) throws Exception {
        holder.getmTxtRepositorioNome().setText(mItems.get(position).getName());
        holder.getmTxtRepositorioDescricao().setText(mItems.get(position).getDescription());
        holder.getmTxtForks().setText(mItems.get(position).getForks_count());
        holder.getmTxtStars().setText(mItems.get(position).getStargazers_count());
        holder.getmTxtUsername().setText(mItems.get(position).getOwner().getLogin());
        holder.getmTxtFullname().setText(mItems.get(position).getFull_name());

        // Baixa cachea e gerencia imagens
        Picasso.with(activity)
                .load(mItems.get(position).getOwner().getAvatar_url())
                .placeholder(R.drawable.ic_profileicon)
                .error(R.drawable.ic_profileicon)
                .into(holder.getmImgProfile());

        holder.getmRepositoryItem().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String login = mItems.get(position).getOwner().getLogin(), repo = mItems.get(position).getName();
                Fragment fragment = RepositoryPullRequestsFragment.newInstance(login, repo);
                mFragmentActionCallback.replaceFragment(fragment);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mItems != null ? mItems.size() : 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView mTxtRepositorioNome;
        private final TextView mTxtRepositorioDescricao;
        private final TextView mTxtForks;
        private final TextView mTxtStars;
        private final TextView mTxtUsername;
        private final TextView mTxtFullname;
        private final TextView mLoadMore;
        private final ImageView mImgProfile;
        private final TextView mIconForks;
        private final TextView mIconStars;
        private final LinearLayout mBlock1;
        private final LinearLayout mBlock2;
        private final LinearLayout mRepositoryItem;

        public ViewHolder(View itemView) {
            super(itemView);

            mBlock1 = (LinearLayout) itemView.findViewById(R.id.block_1);
            mBlock2 = (LinearLayout) itemView.findViewById(R.id.block_2);
            mRepositoryItem = (LinearLayout) itemView.findViewById(R.id.repository_item);

            mTxtRepositorioNome = (TextView) itemView.findViewById(R.id.repositorio_nome);
            mTxtRepositorioDescricao = (TextView) itemView.findViewById(R.id.repositorio_descricao);
            mTxtForks = (TextView) itemView.findViewById(R.id.repository_fork_number);
            mTxtStars = (TextView) itemView.findViewById(R.id.repository_star_number);
            mTxtUsername = (TextView) itemView.findViewById(R.id.repository_user_name);
            mTxtFullname = (TextView) itemView.findViewById(R.id.repository_full_name);
            mLoadMore = (TextView) itemView.findViewById(R.id.load_more);
            mImgProfile = (ImageView) itemView.findViewById(R.id.repository_profile_image);

            mIconForks = (TextView) itemView.findViewById(R.id.ic_forks);
            mIconStars = (TextView) itemView.findViewById(R.id.ic_stars);

            if (mIconForks != null)
                mIconForks.setTypeface(typeFace);
            if (mIconStars != null)
                mIconStars.setTypeface(typeFace);
        }

        public TextView getmTxtRepositorioNome() {
            return mTxtRepositorioNome;
        }

        public TextView getmTxtRepositorioDescricao() {
            return mTxtRepositorioDescricao;
        }

        public TextView getmTxtForks() {
            return mTxtForks;
        }

        public TextView getmTxtStars() {
            return mTxtStars;
        }

        public TextView getmTxtUsername() {
            return mTxtUsername;
        }

        public TextView getmTxtFullname() {
            return mTxtFullname;
        }

        public ImageView getmImgProfile() {
            return mImgProfile;
        }

        public TextView getmLoadMore() {
            return mLoadMore;
        }

        public LinearLayout getmBlock1() {
            return mBlock1;
        }

        public LinearLayout getmBlock2() {
            return mBlock2;
        }

        public LinearLayout getmRepositoryItem() {
            return mRepositoryItem;
        }
    }
}
