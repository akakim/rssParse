package com.akakim.bluehousereaderapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
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
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.akakim.bluehousereaderapp.adapter.BoardCategoryFragmentAdapter;
import com.akakim.bluehousereaderapp.adapter.FeedListAdapter;
import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.data.Constants;
import com.akakim.bluehousereaderapp.data.FeedData;
import com.akakim.bluehousereaderapp.ui.MainBoardCallback;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenter;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenterImpl;
import com.akakim.bluehousereaderapp.ui.activity.BaseActivity;
import com.akakim.bluehousereaderapp.ui.activity.BlueHouseContentActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainJavaActivity extends BaseActivity implements MainBoardCallback, NavigationView.OnNavigationItemSelectedListener {

//    @BindView(R.id.drawerLayout)
//    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

//    @BindView(R.id.navigationView)
//    NavigationView navigationView;

    MainBoardPresenterImpl mainBoardCallback;

    Uri bestLink;

    @BindView( R.id.rvBoardList)
    RecyclerView rvBoardList;

//    @BindView(R.id.tvErrorMessage)
//    TextView tvErrorMessage;


    FeedListAdapter feedListAdapter;


    List<FeedData> feedData = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);

        ButterKnife.bind(this);
        setSupportActionBar(toolbar);



        feedData.add( new FeedData( "청와대 뉴스피드 ", MainBoardPresenter.BLUE_HOUSE_BASIC_TYPE, BlueHouseContentActivity.class ) );
        feedData.add( new FeedData( "청와대 뉴스피드 (추천순)",MainBoardPresenter.BLUE_HOUSE_POPPULAR_TYPE ,BlueHouseContentActivity.class ) );
        feedData.add( new FeedData( "청와대 뉴스피드 (분야별 청원)",MainBoardPresenter.BLUE_HOUSE_CATEGORY_TYPE ,BlueHouseContentActivity.class ) );
//        feedData.add( new FeedData( "청와대 뉴스피드 (답변된 청원)",MainBoardPresenter.BLUE_HOUSE_ANSWER_TYPE ,BlueHouseContentActivity.class ) );

        feedListAdapter = new FeedListAdapter(feedData,this);


        rvBoardList.setLayoutManager( new LinearLayoutManager( this ));
        rvBoardList.setAdapter( feedListAdapter );
        rvBoardList.setNestedScrollingEnabled( false );
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

//   .
    @Override
    public void responseSuccess(@NonNull Bundle boardData) {
        progressDialog.dismiss();

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

//    @OnClick(R.id.btnTest)
//    public void testActivity(){
//
//        Intent i = new Intent(this, BlueHouseContentActivity.class);
////        i.putExtra( "action",BlueHouseContentActivity.INIT_BOARD );
//        startActivity( i );
//    }
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

//        drawerLayout.closeDrawer(GravityCompat.START);
        return false;
    }

}
