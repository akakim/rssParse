package com.akakim.bluehousereaderapp.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.akakim.bluehousereaderapp.ui.view.CustomProgressDialog;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 */

public class BaseActivity extends AppCompatActivity {

    protected CustomProgressDialog progressDialog;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


}
