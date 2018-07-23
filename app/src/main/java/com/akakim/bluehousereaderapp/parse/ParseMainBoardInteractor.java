package com.akakim.bluehousereaderapp.parse;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.util.Map;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 * 게시판을 분석하는 과정의 모음 .
 */

public interface ParseMainBoardInteractor {

    interface OnFinishedListener{
        void onFinished(final boolean isError, String result, Bundle responseData);
    }

    void init();
    void loadBoard(String url);
    void loadBoard(Uri uri);
    void loadNextPage(Uri uri);
    void loadBoard(String url,Map<String,String> getParameter);

}
