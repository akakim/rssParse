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

public class BoardAdapter extends RecyclerView.Adapter<BoardAdapter.BoardViewHolder> {


    private Context                             context;
    private ArrayList<BoardData>                boardDataItems;
    private OnListFragmentInteractionListener   listener;
    private OnListItemClickListener             onListItemClickListener;


    public BoardAdapter( Context context, ArrayList<BoardData> boardDataItems  ){
        this( context,boardDataItems,null);
    }
    public BoardAdapter(Context context, ArrayList<BoardData> boardDataItems , OnListFragmentInteractionListener listener) {
        this.context        = context;
        this.boardDataItems = boardDataItems;
        this.listener       = listener;
    }


    public void setListener(OnListFragmentInteractionListener listener) {
        this.listener = listener;
    }

    public void setOnListItemClickListener(OnListItemClickListener onListItemClickListener) {
        this.onListItemClickListener = onListItemClickListener;
    }

    @Override
    public BoardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BoardViewHolder( LayoutInflater.from(context).inflate(R.layout.blue_house_board_layout,parent,false) );
    }

    @Override
    public void onBindViewHolder(BoardViewHolder holder, final int position) {

        final BoardData item = boardDataItems.get(position);

        holder.itemView.setOnClickListener( (view)->{
            if( listener != null ){
                listener.onListFragmentInteraction( item );
            }

            if(onListItemClickListener != null){
                onListItemClickListener.onListItemClickListener(position,item);
            }

         });
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
        TextView tvBoardIdx;

        @BindView(R.id.tvCategory)
        TextView tvCategory;


        @BindView(R.id.tvTitle)
        TextView tvTitle;

        @BindView(R.id.tvBoardTerm)
        TextView tvBoardTerm;

        @BindView(R.id.tvNumberOfJoinedPeople)
        TextView tvNumberOfJoinedPeople;


        public BoardViewHolder(View itemView) {
            super(itemView);

            ButterKnife.bind(this,itemView);

        }
    }

    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(BoardData item);
    }

    public interface OnListItemClickListener{
        void onListItemClickListener(int position ,BoardData item);
    }
}
