package com.akakim.bluehousereaderapp;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.Toast;
import android.support.v7.widget.Toolbar;

import com.akakim.bluehousereaderapp.ui.MainBoardCallback;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenterImpl;
import com.akakim.bluehousereaderapp.ui.activity.BaseActivity;

import org.json.JSONObject;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainJavaActivity extends BaseActivity implements MainBoardCallback, NavigationView.OnNavigationItemSelectedListener {

    @BindView(R.id.drawerLayout)
    DrawerLayout drawerLayout;

    @BindView(R.id.toolbar)
    Toolbar toolbar;

    @BindView(R.id.navigationView)
    NavigationView navigationView;

    MainBoardPresenterImpl mainBoardCallback;

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

    @Override
    public void responseSuccess(Bundle boardData) {
        progressDialog.dismiss();


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
}
