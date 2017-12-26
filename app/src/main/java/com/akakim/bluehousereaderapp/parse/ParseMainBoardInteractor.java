package com.akakim.bluehousereaderapp.parse;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.akakim.bluehousereaderapp.data.BoardData;

import org.json.JSONObject;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 */

public interface ParseMainBoardInteractor {

    interface OnFinishedListener{
        void onFinished(final boolean isError, String result, JSONObject responseData);
    }

    void init(AppCompatActivity activity);
    void initFragment(Fragment fragment);
}
