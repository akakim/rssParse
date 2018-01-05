package com.akakim.bluehousereaderapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.akakim.bluehousereaderapp.data.BoardData;

import java.util.ArrayList;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2018-01-05
 * @since 0.0.1
 */

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder>{


    Context context;
    ArrayList<BoardData> boardDataItems;

    public BoardAdapter(Context context,ArrayList<BoardData> boardDataItems) {
        this.context = context;
        this.boardDataItems = boardDataItems;
    }

    @Override
    public BoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(BoardViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class BoardViewHolder extends RecyclerView.ViewHolder{

        public BoardViewHolder(View itemView) {
            super(itemView);
        }
    }
}
