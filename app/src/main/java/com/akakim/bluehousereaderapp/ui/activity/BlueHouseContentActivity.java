package com.akakim.bluehousereaderapp.ui.activity;

import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.akakim.bluehousereaderapp.R;
import com.akakim.bluehousereaderapp.adapter.BlueHouseFragmentAdapter;
import com.akakim.bluehousereaderapp.adapter.BoardAdapter;
import com.akakim.bluehousereaderapp.adapter.BoardCategoryFragmentAdapter;
import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.data.FeedData;
import com.akakim.bluehousereaderapp.parse.ParseMainBoardInteractor;
import com.akakim.bluehousereaderapp.ui.BlueHouseFragment;
import com.akakim.bluehousereaderapp.ui.MainBoardCallback;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenterImpl;
import com.akakim.bluehousereaderapp.ui.view.SwipeRefreshLayoutBottom;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * activity 단위로 화면을 호출.
 */
public class BlueHouseContentActivity extends BaseActivity implements MainBoardCallback {


    public static String INIT_BOARD             ="com.akakim.bluehousereaderapp.ui.activity.BlueHouseContentActivity.recentlyUpdated";
    public static String INIT_RECOMEND_BOARD    ="com.akakim.bluehousereaderapp.ui.activity.BlueHouseContentActivity.recommendedBoard";


    @BindView(R.id.toolbar)
    Toolbar toolbar;

    MainBoardPresenterImpl mainBoardCallback;

    @BindView(R.id.blueHouseHeader)
    LinearLayoutCompat blueHouseHeader;

    @BindViews({
            R.id.tvBestOpinionTitle,
            R.id.tvBestOpinionContent,
            R.id.tvBestOpinionId,
            R.id.tvBestOpinionCount,
            R.id.tvBestOpinionDateAmount
    })
    List<TextView> bestOpinionList = new ArrayList<TextView>();

    Uri bestLink;


//    @BindView(R.id.tvErrorMessage)
//    TextView tvErrorMessage;


    @BindView(R.id.rvBoardViewPager)
    ViewPager viewPager;


    BlueHouseFragmentAdapter blueHouseFragmentAdapter;
    ArrayList<BoardData> boardDataArrayList = new ArrayList<>();
    ArrayList<BlueHouseFragment> blueHouseFragmentArrayList = new ArrayList<>();
    ArrayList<ArrayList<BoardData>> boardDataListList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blue_house_content);

        ButterKnife.bind(this);
//        setSupportActionBar(toolbar);


        mainBoardCallback = new MainBoardPresenterImpl(this );

        String getData =getIntent().getStringExtra(FeedData.FEED_ITEM_KEY);
        mainBoardCallback.initContent( getData );


        for (int k =0 ;k<3;k++){
            BlueHouseFragment fragment = new BlueHouseFragment();

            blueHouseFragmentArrayList.add (new BlueHouseFragment() );
        }
        blueHouseFragmentAdapter = new BlueHouseFragmentAdapter(getSupportFragmentManager(),blueHouseFragmentArrayList,boardDataListList );
        viewPager.setAdapter(  blueHouseFragmentAdapter );



//        swLayout.setOnRefreshListener(new SwipeRefreshLayoutBottom.OnRefreshListener() {
//            @Override
//            public void onRefresh() {
//                Toast.makeText(BlueHouseContentActivity.this,"새로 갱신한다!",Toast.LENGTH_SHORT).show();
//            }
//        });


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

        if( isBestBoardShow ){
            blueHouseHeader.setVisibility( View.VISIBLE);
        }else {
            blueHouseHeader.setVisibility( View.GONE);
        }

        if( isAnswerBoardShow ){

        }

        for(BoardData item : boardItems){
            Log.d(getClass().getSimpleName()," boardItems " + boardItems.toString());
            switch ( item.getBoardTag() ){

                case BoardData.BEST_BOARD_TAG:
                    bestOpinionList.get(0).setText( item.getTitle() );
                    bestOpinionList.get(1).setText( item.getThumbnailContent() );
                    bestOpinionList.get(2).setText( "청원인 : " + item.getAuthor() );
                    bestOpinionList.get(3).setText( "신청인 : " + item.getNumberOfJoinPeople() );
                    bestOpinionList.get(4).setText( "청원기간 : " + item.getTerm() );

                    bestLink = Uri.parse( item.getLink() );
                    break;
//                case BoardData.READY_ANSWER_BOARD_TAG:
//                    break;
//                case BoardData.NORMAL_BOARD_TAG:
//                    boardDataArrayList.add( item );
//                    break;
                default:
                    break;
            }
        }

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

//    @OnClick(R.id.tvErrorMessage)
//    public void refreshBoard(){
//
//    }
}
