package com.akakim.bluehousereaderapp.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2018-07-22
 * @since 0.0.1
 */

public class WrappingLayoutManager extends LinearLayoutManager {
    public WrappingLayoutManager(Context context) {
        super(context);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        try {
            super.onLayoutChildren(recycler, state);
        }catch ( IndexOutOfBoundsException e ){

            Log.e(getClass().getSimpleName(),"",e.getCause());
        }
    }
}
