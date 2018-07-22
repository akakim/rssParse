package com.akakim.bluehousereaderapp.adapter.viewholder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.akakim.bluehousereaderapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2018-07-22
 * @since 0.0.1
 */

public class BoardViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tvBoardIdx)
        public TextView tvBoardIdx;

        @BindView(R.id.tvCategory)
        public TextView tvCategory;

        @BindView(R.id.tvTitle)
        public TextView tvTitle;

        @BindView(R.id.tvBoardTerm)
        public TextView tvBoardTerm;

        @BindView(R.id.tvNumberOfJoinedPeople)
        public TextView tvNumberOfJoinedPeople;

        public BoardViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

        }
 }