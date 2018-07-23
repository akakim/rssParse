package com.akakim.bluehousereaderapp.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akakim.bluehousereaderapp.R;
import com.akakim.bluehousereaderapp.adapter.viewholder.BoardViewHolder;
import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.adapter.viewholder.ProgressViewHolder;

import java.util.ArrayList;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2018-07-22
 * @since 0.0.1
 */

public class FloodingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>{

    Context context;
    ArrayList< ? extends BoardData> boardDataItems;


    public FloodingAdapter(Context context, ArrayList<? extends BoardData> boardData) {
        this.context    = context;
        this.boardDataItems  = boardData;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if( viewType == 0){

            return new ProgressViewHolder(LayoutInflater.from( context ).inflate(R.layout.item_progress,parent,false));
        }else {

            return new BoardViewHolder( LayoutInflater.from(context).inflate(R.layout.blue_house_board_layout,parent,false) );
        }

    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        if( holder instanceof BoardViewHolder){
            BoardViewHolder viewHolder =(BoardViewHolder) holder;
            final BoardData item = boardDataItems.get(position);

            viewHolder.itemView.setOnClickListener( (view)->{
//                if( listener != null ){
//                    listener.onListFragmentInteraction( item );
//                }
//
//                if(onListItemClickListener != null){
//                    onListItemClickListener.onListItemClickListener(position,item);
//                }

            });

            viewHolder.tvBoardIdx.setText( item.getNumberOfContent() );
            viewHolder.tvCategory.setText( item.getCategory() );
            viewHolder.tvNumberOfJoinedPeople.setText( item.getNumberOfJoinPeople() );
            viewHolder.tvBoardTerm.setText( item.getTerm());
            viewHolder.tvTitle.setText( item.getThumbnailContent() )  ;

        }
    }


    public void addDummyProgressView(){

        boardDataItems.add(null);
        notifyItemInserted( boardDataItems.size() -1 );
    }

    public void removeDummyProgressView(){
        if( boardDataItems.size() > 0 ){

            int idx = boardDataItems.size() -1 ;
            boardDataItems.remove( idx );
            notifyItemRemoved( idx );
        }
    }
    public boolean isLoading(){

//        if ( boardDataItems.get( boardDataItems.size() ) == null ) true : false

        if( boardDataItems.size() == 0 )  return false;

        return boardDataItems.get( boardDataItems.size() -1 ) == null;
    }
    @Override
    public int getItemCount() {
        return boardDataItems.size();
    }

    @Override
    public int getItemViewType(int position) {

        if( boardDataItems.get(position) == null ){
            return 0;
        }else {
            return 1;
       }
    }
}
