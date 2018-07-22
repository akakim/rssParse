package com.akakim.bluehousereaderapp.ui;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.akakim.bluehousereaderapp.Util;
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
 *  게시판을 파싱하는 Presenter의 구현체이다.
 *
 *  1. 이것을 인터페이스를 구현 하는 부분( Activity 등등 ) initContent 혹은 UpdateContent를 호출한다.
 *  2. 어떤 컨텐츠를 원하든, ParseInteractor 에서 게시판을 분석한다.
 *  3. 분석이 끝나면 UI와 파싱한 결과물들을 호출한 쪽으로 보내준다.
 */

public class MainBoardPresenterImpl implements MainBoardPresenter,ParseMainBoardInteractor.OnFinishedListener {


    MainBoardCallback  mainBoardCallback;
    ParseMainBoardImpl parseMainBoard;

    // 업데이트시 URL을 재활용하는 빌더 객체
    Uri.Builder basicBuilder;
    // TODO : ENUM을 이용하면, 코딩하는 과정에서 실수를 줄일 수 있다.
    public MainBoardPresenterImpl(MainBoardCallback mainBoardCallback) {
        this.mainBoardCallback = mainBoardCallback;


        parseMainBoard = new ParseMainBoardImpl(this);
    }



    @Override
    public void onFinished(boolean isError,String result, Bundle responseData ) {
        Log.d(getClass().getSimpleName(), "onFinished result : " + result );
        Runnable failRunnable = ()-> { mainBoardCallback.responseFailed(result);  };
        Runnable successRunnable = ()-> { mainBoardCallback.responseSuccess( responseData );};

        if( isError ){
            mainBoardCallback.setUI( failRunnable );
        }else {
            mainBoardCallback.setUI( successRunnable );
        }
    }

    @Override
    public void initContent(@NonNull  String type) {
        mainBoardCallback.showMessage("데이터 로딩 중");


        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("www1.president.go.kr");

        switch (type){
            case BLUE_HOUSE_BASIC_TYPE:
                builder.path("petitions");
                break;
            case BLUE_HOUSE_POPPULAR_TYPE:
                builder.path("petitions");
                builder.appendQueryParameter("order","best");
                break;
            case BLUE_HOUSE_CATEGORY_TYPE:
                builder.path("petitions/category");

                break;
            case BLUE_HOUSE_ANSWER_TYPE:
                builder.path("petitions/answer");
                break;
        }
        basicBuilder = builder;

        parseMainBoard.loadBoard( builder.build());


    }

    @Override
    public void updateConent(@NonNull String type) {

        //TODO :
        switch (type){
            case BLUE_HOUSE_BASIC_TYPE:
                break;
            case BLUE_HOUSE_POPPULAR_TYPE:
                break;
            case BLUE_HOUSE_CATEGORY_TYPE:
                break;
            case BLUE_HOUSE_ANSWER_TYPE:
                break;
        }
    }
}
