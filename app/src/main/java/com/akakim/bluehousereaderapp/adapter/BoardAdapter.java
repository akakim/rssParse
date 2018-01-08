package com.akakim.bluehousereaderapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.akakim.bluehousereaderapp.R;
import com.akakim.bluehousereaderapp.data.BoardData;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

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
        return new BoardViewHolder( LayoutInflater.from(context).inflate(R.layout.blue_house_board_layout,null,false) );
    }

    @Override
    public void onBindViewHolder(BoardViewHolder holder, int position) {

        final BoardData item = boardDataItems.get(position);

        holder.tvBoardIdx.setText( item.getBoardIdx() );
        holder.tvCategory.setText( item.getCategory() );
        holder.tvNumberOfJoinedPeople.setText( item.getNumberOfJoinPeople() );
        holder.tvBoardTerm.setText( item.getTerm());
        holder.tvTitle.setText( item.getTitle() )  ;
    }

    @Override
    public int getItemCount() {
        return boardDataItems.size();
    }

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
}
