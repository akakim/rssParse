package com.akakim.bluehousereaderapp.ui;

import android.support.v7.app.AppCompatActivity;

import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.parse.ParseMainBoardImpl;
import com.akakim.bluehousereaderapp.parse.ParseMainBoardInteractor;
import com.akakim.bluehousereaderapp.ui.activity.BaseActivity;

import org.json.JSONObject;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 */

public class MainBoardPresenterImpl implements MainBoardPresenter,ParseMainBoardInteractor.OnFinishedListener {


    MainBoardCallback mainBoardCallback;
    ParseMainBoardImpl parseMainBoard;
    BaseActivity activity;


    public MainBoardPresenterImpl(MainBoardCallback mainBoardCallback, BaseActivity activity) {
        this.mainBoardCallback = mainBoardCallback;
        this.activity = activity;

        parseMainBoard = new ParseMainBoardImpl(this);
    }

    @Override
    public void initContent() {
        mainBoardCallback.showMessage("데이터 로딩중");
        parseMainBoard.init(activity);

    }

    @Override
    public void onFinished(boolean isError,String result, JSONObject responseData ) {

        if( isError ){
            mainBoardCallback.responseFailed(result);
        }else {
            mainBoardCallback.responseSuccess(responseData);
        }
    }
}
