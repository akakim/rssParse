package com.akakim.bluehousereaderapp;

import android.os.Bundle;
import android.widget.Toast;

import com.akakim.bluehousereaderapp.ui.MainBoardCallback;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenterImpl;
import com.akakim.bluehousereaderapp.ui.activity.BaseActivity;

import org.json.JSONObject;

public class MainJavaActivity extends BaseActivity implements MainBoardCallback {

    MainBoardPresenterImpl mainBoardCallback;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_java);


        mainBoardCallback = new MainBoardPresenterImpl(this , this);

        mainBoardCallback.initContent();



    }

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
        Toast.makeText(this,result,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void responseSuccess(JSONObject boardData) {
        progressDialog.dismiss();


    }

    @Override
    public void showMessage(String msg) {

    }

    @Override
    public void showAlert() {

    }
}
