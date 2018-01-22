package com.akakim.bluehousereaderapp.ui.fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.akakim.bluehousereaderapp.R;
import com.akakim.bluehousereaderapp.adapter.BoardAdapter;
import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.ui.MainBoardCallback;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenter;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenterImpl;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link BlueHouseFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link BlueHouseFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BlueHouseFragment extends Fragment implements MainBoardCallback,BoardAdapter.OnListFragmentInteractionListener {


    public static final String URL = "page";

    static String pageURL = "0";
    private OnFragmentInteractionListener mListener;


    @BindView(R.id.boardList)
    RecyclerView boardList;



    ArrayList<BoardData> boardDataArrayList = new ArrayList<>();
    static MainBoardPresenter mainBoardPresenter;


    BoardAdapter boardAdapter;
    public BlueHouseFragment() {
        // Required empty public constructor
    }


    public static BlueHouseFragment newInstance(String page) {
        BlueHouseFragment fragment = new BlueHouseFragment();
        Bundle args = new Bundle();

        pageURL = args.getString(URL);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            pageURL = getArguments().getString(URL);
        }

        mainBoardPresenter= new MainBoardPresenterImpl( this );
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View inflateView  =inflater.inflate(R.layout.fragment_blue_house, container, false);

//        ButterKnife.bind( inflateView);
        ButterKnife.bind(this,inflateView);
        boardAdapter = new BoardAdapter(getContext(), boardDataArrayList,this );
        boardList.setAdapter ( boardAdapter );
        boardList.setLayoutManager(new LinearLayoutManager( getContext() ));



        return inflateView;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mainBoardPresenter.initContent(pageURL );
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onResumeRefresh() {

    }

    @Override
    public void showProgress(String message) {

    }

    @Override
    public void responseFailed(String result) {

        Log.e(getClass().getSimpleName(),"on Response Error " + result ) ;
        if( getContext() != null){
            Toast.makeText( getContext(),result,Toast.LENGTH_SHORT).show();
        }else {
            Log.e(getClass().getSimpleName(),"Foooo getContext is null Error Message not be showing");
        }
    }

    @Override
    public void responseSuccess(Bundle boardData) {

        ArrayList<BoardData> boardItems = boardData.getParcelableArrayList( BoardData.BOARD_ITEMS_KEY );


        for(BoardData item : boardItems){
            Log.d(getClass().getSimpleName()," boardItems " + boardItems.toString());
            switch ( item.getBoardTag() ){


                case BoardData.NORMAL_BOARD_TAG:
                    boardDataArrayList.add( item );
                    break;
                default:
                    break;
            }
        }
//
//        int maxHeight= 0;
//        for (int k = 0; k< boardDataArrayList.size();k++){
//
//
//
//            View view =  boardList.getLayoutManager().getChildAt(k);
//            int height =  view.getMeasuredHeight();
//
//            int top = view.getPaddingTop();
//            int bottom = view.getPaddingBottom();
//
//
//            maxHeight += maxHeight + ( height + top + bottom );
////            boardList.
//        }

        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams( boardList.getLayoutParams() ) ;

        layoutParams.height = 2000;
        boardAdapter.notifyDataSetChanged();
//        Runnable setupUI = new Runnable() {
//            @Override
//            public void run() {
//
//
//
//                for (int k = 0; k< boardDataArrayList.size();k++){
//
//
////                    LayoutInflater.from()
//                    View view =  boardList.getLayoutManager().getChildAt(k);
//                    int height =  view.getMeasuredHeight();
//
//                    int top = view.getPaddingTop();
//                    int bottom = view.getPaddingBottom();
//
//                    ViewGroup.LayoutParams params = new ViewGroup.LayoutParams(view.getLayoutParams());
//                    params.height = top+height+bottom;
//
//                    RecyclerView.ViewHolder viewHolder = new RecyclerView.ViewHolder(view) {
//                        @Override
//                        public String toString() {
//                            return super.toString();
//                        }
//                    };
//                    boardList.getAdapter().onBindViewHolder(viewHolder,k);
//
//                }
//
//
//
//
//
//            }
//        };
//        boardAdapter.notifyDataSetChanged();

//        Log.d(getClass().getSimpleName(),"get RecyclerView Size : " + boardAdapter.getItemCount() );
        Log.d(getClass().getSimpleName(),"get recyclerView Size MeasuredHeight = "+        boardList.getMeasuredHeight());
        Log.d(getClass().getSimpleName(),"get recyclerView Size MeasuredHeight = "+        boardList.getMeasuredHeightAndState());
    }

    @Override
    public void setUI(Runnable runnable) {

        if ( getActivity() != null ) {

            getActivity().runOnUiThread( runnable );
        }else {

            if ( getContext() != null ){
                Toast.makeText( getContext()," 뉴스 업데이트를 실패했습니다.",Toast.LENGTH_SHORT).show();
            }else {
                Log.e(getClass().getSimpleName(),"뉴스 업데이트 실패");
            }

        }
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showAlert() {

    }

    @Override
    public void onListFragmentInteraction(BoardData item) {

    }


    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }




}
