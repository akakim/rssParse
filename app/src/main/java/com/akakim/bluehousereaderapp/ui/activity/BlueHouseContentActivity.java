package com.akakim.bluehousereaderapp.ui.activity;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.akakim.bluehousereaderapp.R;
import com.akakim.bluehousereaderapp.Util;
import com.akakim.bluehousereaderapp.adapter.BlueHouseFragmentAdapter;
import com.akakim.bluehousereaderapp.adapter.BoardAdapter;
import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.data.FeedData;
import com.akakim.bluehousereaderapp.ui.FloodingPresenter;
import com.akakim.bluehousereaderapp.ui.fragment.BlueHouseFragment;
import com.akakim.bluehousereaderapp.ui.MainBoardCallback;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenterImpl;
import com.akakim.bluehousereaderapp.ui.fragment.SampleBlackFragment;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

/**
 * activity 단위로 화면을 호출.
 */
public class BlueHouseContentActivity extends BaseActivity
        implements MainBoardCallback,
            BlueHouseFragment.OnFragmentInteractionListener,
            SampleBlackFragment.OnFragmentInteractionListener,
            BoardAdapter.OnListItemClickListener,
            View.OnClickListener,
            FloodingPresenter.LoadListener{


//    public static String INIT_BOARD             ="com.akakim.bluehousereaderapp.ui.activity.BlueHouseContentActivity.recentlyUpdated";
//    public static String INIT_RECOMEND_BOARD    ="com.akakim.bluehousereaderapp.ui.activity.BlueHouseContentActivity.recommendedBoard";

//    // TODO : ENUM을 이용하면, 코딩하는 과정에서 실수를 줄일 수 있다.


    String type;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    MainBoardPresenterImpl mainBoardCallback;

    ArrayList<BoardData> boardDataArrayList             = new ArrayList<>();
    ArrayList<Fragment> blueHouseFragmentArrayList      = new ArrayList<>();
    ArrayList<ArrayList<BoardData>> boardDataListList   = new ArrayList<>();


    Map<String,String> getParmeterMap = new HashMap<>();


    @BindView( R.id.rvBoardList)
    RecyclerView rvBoardList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_house_content);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        // id가 동적으로 할당할 수 없기 때문에 특이케이스
        toolbar.setNavigationOnClickListener( new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                finish();
            }
        } );

        mainBoardCallback = new MainBoardPresenterImpl(this );

        type = getIntent().getStringExtra(FeedData.FEED_TYPE_ITEM );



        mainBoardCallback.initContent( type );



        // Legacy
//        for (int k =0 ;k<3;k++){
//
//            BlueHouseFragment fragment = new BlueHouseFragment();
//
//
//            getParmeterMap.put(BlueHouseFragment.URL,String.valueOf(k));
////            String dynamicURL = Util.getURLFactory( getFeedURLItem,getParmeterMap );
//
////            Log.d(getClass().getSimpleName(),"on Create URL " + dynamicURL );
//
//            Bundle bundle = new Bundle();
//
////            bundle.putString(BlueHouseFragment.URL, dynamicURL );
//
//
//
//            fragment.setArguments(bundle );
//            blueHouseFragmentArrayList.add  (fragment );
//        }

    }


    @Override
    protected void onResume() {
        super.onResume();
        onResumeRefresh();
    }

    /**
     * 화면에 다시 그려야할때
     */
    @Override
    public void onResumeRefresh() {

    }

    @Override
    public void showProgress(String message) {

        progressDialog.setContent( message );
        progressDialog.show();
    }

    @Override
    public void responseFailed(String result) {

        progressDialog.dismiss();

        if( result != null){
            if( "timeout".contains(result)){
                Log.d("onResponseTimeOutFailed",result);
            }
        }
    }


    @Override
    public void responseSuccess(@NonNull Bundle boardData) {
        progressDialog.dismiss();

        ArrayList<BoardData> boardItems = boardData.getParcelableArrayList( BoardData.BOARD_ITEMS_KEY );

        Boolean isBestBoardShow = boardData.getBoolean( BoardData.BEST_BOARD_TAG );
        Boolean isAnswerBoardShow = boardData.getBoolean( BoardData.READY_ANSWER_BOARD_TAG);

        BoardAdapter adapter = new BoardAdapter(this, boardItems );

        adapter.setOnListItemClickListener( this );
        rvBoardList.setLayoutManager(new LinearLayoutManager(this));
        rvBoardList.setAdapter( adapter );


//        for(BoardData item : boardItems){
//            Log.d(getClass().getSimpleName()," boardItems " + boardItems.toString());
//            switch ( item.getBoardTag() ){
//
//                case BoardData.BEST_BOARD_TAG:
//                    bestOpinionList.get(0).setText( item.getTitle() );
//                    bestOpinionList.get(1).setText( item.getThumbnailContent() );
//                    bestOpinionList.get(2).setText( "청원인 : " + item.getAuthor() );
//                    bestOpinionList.get(3).setText( "신청인 : " + item.getNumberOfJoinPeople() );
//                    bestOpinionList.get(4).setText( "청원기간 : " + item.getTerm() );
//
//                    bestLink = Uri.parse( item.getLink() );
//                    break;
//                case BoardData.READY_ANSWER_BOARD_TAG:
//                    break;
//                case BoardData.NORMAL_BOARD_TAG:
//                    boardDataArrayList.add( item );
//                    break;
//                default:
//                    break;
//            }
//        }



    }

    @Override
    public void setUI(Runnable runnable) {

        runOnUiThread( runnable );
    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showAlert() {

    }

    @Override
    public void onFragmentInteraction(Uri uri) {

    }


    @Override
    public void onListItemClickListener(int position, BoardData item) {

    }

    @Override
    public void onClick(View v) {

//        Toast.makeText( this ,"getView Id : " + v.getId(),Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoad() {

    }
}
