package com.akakim.bluehousereaderapp.ui.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.akakim.bluehousereaderapp.R;
import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.ui.MainBoardCallback;
import com.akakim.bluehousereaderapp.ui.MainBoardPresenter;

public class FullBoardContentActivity extends AppCompatActivity {


    MainBoardCallback mainBoardCallback;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_board_content);




    }

}
