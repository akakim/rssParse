package com.akakim.bluehousereaderapp.adapter.viewholder;

import android.support.v7.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.akakim.bluehousereaderapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2018-07-22
 * @since 0.0.1
 */

public class ProgressViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public ProgressViewHolder(View itemView) {
        super(itemView);

        ButterKnife.bind(this,itemView);

//        Log.d( "ProgressViewHolder" , )
//        itemView.getAccessibilityClassName()
//        ViewGroup.LayoutParams params = itemView.getLayoutParams();


    }
}
