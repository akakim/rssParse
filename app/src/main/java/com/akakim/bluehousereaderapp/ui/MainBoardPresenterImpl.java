package com.akakim.bluehousereaderapp.ui;

import android.os.Bundle;
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


    public MainBoardPresenterImpl(MainBoardCallback mainBoardCallback) {
        this.mainBoardCallback = mainBoardCallback;


        parseMainBoard = new ParseMainBoardImpl(this);
    }

    @Override
    public void initContent() {
//        mainBoardCallback.showMessage("데이터 로딩중");
//        parseMainBoard.init();

    }

    @Override
    public void initContent(@ParseMainBoardInteractor.BoardDefinition int def) {

        mainBoardCallback.showMessage("데이터 로딩중");
        parseMainBoard.loadBoard( def );

    }

    @Override
    public void initContent(String url) {
        mainBoardCallback.showMessage("데이터 로딩중");
        parseMainBoard.loadBoard( url );
    }


    @Override
    public void onFinished(boolean isError,String result, Bundle responseData ) {

        Runnable failRunnable = ()-> { mainBoardCallback.responseFailed(result);  };
        Runnable successRunnable = ()-> { mainBoardCallback.responseSuccess( responseData );};


        if( isError ){
            mainBoardCallback.setUI( failRunnable );
        }else {
            mainBoardCallback.setUI( successRunnable );
        }
    }
}
