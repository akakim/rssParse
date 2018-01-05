package com.akakim.bluehousereaderapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.ui.MainBoardCallback;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenterImpl;
import com.akakim.bluehousereaderapp.ui.activity.BaseActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainJavaActivity extends BaseActivity implements MainBoardCallback, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigationView)
    NavigationView navigationView;

    MainBoardPresenterImpl mainBoardCallback;


//    @BindView(R.id.cvItemBoardThumbnail)
//    TextView cvItemBoardThumbnail;

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

//    @BindView( R.id.rvBoardList)
//    RecyclerView rvBoardList;

    @BindView(R.id.tabLayout)
    TabLayout tabLayout;

    @BindView(R.id.viewPager)
    ViewPager viewPager;

    @BindView(R.id.tvErrorMessage)
    TextView tvErrorMessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this,drawerLayout,toolbar,
                R.string.main_navigation_drawer_open,
                R.string.main_navigation_drawer_close
        );


        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        navigationView.setNavigationItemSelectedListener(this);

        mainBoardCallback = new MainBoardPresenterImpl(this , this);

        mainBoardCallback.initContent();


//        rvBoardList.setLayoutManager( new LinearLayoutManager(this));

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
//        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }

    @OnClick(R.id.btnShowDetail)
    public void showDetail(){

        if ( bestLink == null){
            Toast.makeText(this,"초기화 오류",Toast.LENGTH_SHORT ).show();
        }else {

            Intent showAction = new Intent(Intent.ACTION_VIEW,bestLink);
            startActivity(showAction);
        }
    }
    @Override
    public void responseSuccess(@NonNull Bundle boardData) {
        progressDialog.dismiss();

        ArrayList<BoardData> boardItems = boardData.getParcelableArrayList( BoardData.BOARD_ITEMS_KEY );


        Boolean isBestBoardShow = boardData.getBoolean( BoardData.BEST_BOARD_TAG );
        Boolean isAnswerBoardShow = boardData.getBoolean( BoardData.READY_ANSWER_BOARD_TAG);

        if( isBestBoardShow ){
            blueHouseHeader.setVisibility( View.GONE);
        }else {
            blueHouseHeader.setVisibility( View.VISIBLE);
        }

        if( isAnswerBoardShow ){

        }
//        rvBoardList.

//        boardList
        for(BoardData item : boardItems){
            switch ( item.getBoardTag() ){
                case BoardData.BEST_BOARD_TAG:
                    bestOpinionList.get(0).setText( item.getTitle() );
                    bestOpinionList.get(1).setText( item.getThumbnailContent() );
                    bestOpinionList.get(2).setText( "청원인 : " + item.getAuthor() );
                    bestOpinionList.get(3).setText( "신청인 : " + item.getNumberOfJoinPeople() );
                    bestOpinionList.get(4).setText( "청원기간 : " + item.getTerm() );

                    bestLink = Uri.parse( item.getLink() );
                    break;

                case BoardData.READY_ANSWER_BOARD_TAG:
                    break;
                case BoardData.NORMAL_BOARD_TAG:
                    break;
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

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

    @OnClick(R.id.tvErrorMessage)
    public void refreshBoard(){
    }
}
