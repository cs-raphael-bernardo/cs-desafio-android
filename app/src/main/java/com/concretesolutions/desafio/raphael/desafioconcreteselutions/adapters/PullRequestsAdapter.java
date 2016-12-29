package com.concretesolutions.desafio.raphael.desafioconcreteselutions.adapters;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.concretesolutions.desafio.raphael.desafioconcreteselutions.R;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.interfaces.OnFragmentActionListener;
import com.concretesolutions.desafio.raphael.desafioconcreteselutions.rest.models.pulls.Pull;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.RequestCreator;

import java.util.List;

/**
 * Created by Raphael on 11/11/2016.
 */
public class PullRequestsAdapter extends RecyclerView.Adapter<PullRequestsAdapter.ViewHolder> {

    private List<Pull> mDataset;
    private final OnFragmentActionListener mListener;

    @Override
    public PullRequestsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // create a new view
        LinearLayout v = (LinearLayout) LayoutInflater.from(parent.getContext())
                .inflate(R.layout.card_view_pull, parent, false);
        // set the view's size, margins, paddings and layout parameters

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final PullRequestsAdapter.ViewHolder holder, final int position) {
        CardView cv = (CardView) holder.v.findViewById(R.id.card_view);
        cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = mDataset.get(position).getHtml_url();
                Intent i = new Intent(Intent.ACTION_VIEW).setData(Uri.parse(url));
                holder.v.getContext().startActivity(i);
            }
        });

        ((TextView)holder.v.findViewById(R.id.pull_user_name)).setText(mDataset.get(position).getUser().getLogin());
        ((TextView)holder.v.findViewById(R.id.pull_title)).setText(mDataset.get(position).getTitle());
        ((TextView)holder.v.findViewById(R.id.pull_description)).setText(mDataset.get(position).getBody());
        ((TextView)holder.v.findViewById(R.id.pull_date)).setText(String.valueOf(mDataset.get(position).getCreatedAt()));

        drawPicture((ImageView)holder.v.findViewById(R.id.pull_contact_icon_location_list_item),
                mDataset.get(position).getUser().getAvatar_url());

    }

    private void drawPicture(ImageView viewById, String rewardPic) {
        RequestCreator rc = Picasso.with(viewById.getContext())
                .load(rewardPic);
        rc.placeholder(R.drawable.ic_profileicon);
        rc.error(R.drawable.ic_profileicon);
        rc.into(viewById);
    }

    @Override
    public int getItemCount() {
        return mDataset != null ? mDataset.size() : 0;
    }

    public PullRequestsAdapter(OnFragmentActionListener mListener, List<Pull> myDataset) {
        this.mDataset = myDataset;
        this.mListener = mListener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public LinearLayout v;
        public ViewHolder(LinearLayout v) {
            super(v);
            this.v = v;
        }
    }
}
