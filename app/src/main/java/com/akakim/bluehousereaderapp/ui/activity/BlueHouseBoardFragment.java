package com.akakim.bluehousereaderapp.ui.activity;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.akakim.bluehousereaderapp.R;
import com.akakim.bluehousereaderapp.adapter.BoardAdapter;
import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.data.Constants;
import com.akakim.bluehousereaderapp.ui.MainBoardCallback;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenter;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenterImpl;
import com.akakim.bluehousereaderapp.ui.activity.dummy.DummyContent;
import com.akakim.bluehousereaderapp.ui.activity.dummy.DummyContent.DummyItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class BlueHouseBoardFragment extends Fragment implements MainBoardCallback {


    public static final String IS_UPDATE_KEY = "update";
    public static final String PAGE_NUMBER_KEY = "pageNumber";
    boolean isUpdate = false;

    String pageNumber = "1";
    ArrayList<BoardData> boardDataArrayList ;
    // TODO: Customize parameter argument names
    private static final String ARG_COLUMN_COUNT = "column-count";
    // TODO: Customize parameters
    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener;

    MainBoardPresenter mainBoardPresenter;

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public BlueHouseBoardFragment() {
    }


    public static BlueHouseBoardFragment newInstance(int columnCount) {
        BlueHouseBoardFragment fragment = new BlueHouseBoardFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_COLUMN_COUNT, columnCount);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            mColumnCount = getArguments().getInt(ARG_COLUMN_COUNT);
//            isUpdate = getArguments().getBoolean(IS_UPDATE_KEY,false);
            pageNumber= getArguments().getString(PAGE_NUMBER_KEY,"1");

//            boardDataArrayList = getArguments().getParcelableArrayList( BoardData.BOARD_ITEMS_KEY );
        }
        mainBoardPresenter= new MainBoardPresenterImpl(this  );

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);

        // Set the adapter
        if (view instanceof RecyclerView) {
            Context context = view.getContext();
            RecyclerView recyclerView = (RecyclerView) view;
            if (mColumnCount <= 1) {
                recyclerView.setLayoutManager(new LinearLayoutManager(context));
            } else {
                recyclerView.setLayoutManager(new GridLayoutManager(context, mColumnCount));
            }

            if ( boardDataArrayList == null ){
                // parcelable 객체가 없는경우
                boardDataArrayList = new ArrayList<>();
                Log.d(getClass().getSimpleName(),"Message... ");
            }
            if( getContext() != null ) {
                recyclerView.setAdapter(new BoardAdapter(getContext(), boardDataArrayList, mListener));
                Log.e(getClass().getSimpleName(),"Foo! getContext is null ");
            }
        }
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Map<String,String> map = new HashMap<>(1);
//        map.put("page",PAGE_NUMBER_KEY);
        mainBoardPresenter.initContent(Constants.PUBLIC_OPINION_BASE_URL+"?page="+PAGE_NUMBER_KEY);

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnListFragmentInteractionListener");
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

    }

    @Override
    public void responseSuccess(Bundle boardData) {

        ArrayList<BoardData> boardItems = boardData.getParcelableArrayList( BoardData.BOARD_ITEMS_KEY );


        boardDataArrayList.clear();

        for(BoardData item : boardItems){
            Log.d(getClass().getSimpleName()," boardItems " + boardItems.toString());
            switch ( item.getBoardTag() ){

//                case BoardData.BEST_BOARD_TAG:
//                    bestOpinionList.get(0).setText( item.getTitle() );
//                    bestOpinionList.get(1).setText( item.getThumbnailContent() );
//                    bestOpinionList.get(2).setText( "청원인 : " + item.getAuthor() );
//                    bestOpinionList.get(3).setText( "신청인 : " + item.getNumberOfJoinPeople() );
//                    bestOpinionList.get(4).setText( "청원기간 : " + item.getTerm() );
//
//                    bestLink = Uri.parse( item.getLink() );
//                    break;
                case BoardData.READY_ANSWER_BOARD_TAG:
                    break;
                case BoardData.NORMAL_BOARD_TAG:
                    boardDataArrayList.add( item );
                    break;
                default:
                    break;
            }
        }


    }

    @Override
    public void setUI(Runnable runnable) {
        if( getActivity() != null) {
            getActivity().runOnUiThread(runnable);
        }
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showAlert() {

    }


    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(BoardData item);
    }
}
