package com.akakim.bluehousereaderapp.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akakim.bluehousereaderapp.R;
import com.akakim.bluehousereaderapp.data.FeedData;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by RyoRyeong Kim on 2018-01-19.
 */

public class FeedListAdapter extends RecyclerView.Adapter<FeedListAdapter.ViewHolder>{



    public static String URL_ITEM_KEY= "url";
    List<FeedData> feedDataArrayList = new ArrayList<>();

    Context context;

    public FeedListAdapter(List<FeedData> feedDataArrayList, Context contex) {
        this.feedDataArrayList = feedDataArrayList;
        this.context = contex;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.feed_list,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        final FeedData data = feedDataArrayList.get(position);
        holder.tvFeedName.setText( data.getFeedName() );

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent i = new Intent( context, data.getaClass() );

                i.putExtra(FeedData.FEED_TYPE_ITEM,data.getType());

                context.startActivity( i );
            }
        });

    }

    @Override
    public int getItemCount() {
        return feedDataArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        @BindView( R.id.tvFeedName)
        public TextView tvFeedName;
        public ViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);
        }
    }
}
