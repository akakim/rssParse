package com.akakim.bluehousereaderapp.parse;

import android.os.Bundle;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 */

public interface ParseMainBoardInteractor {


    int BLUE_HOUSE_INIT_BOARD = 0;
    int BLUE_HOUSE_RECOMAND_MAIN = 1;




    @IntDef( {BLUE_HOUSE_INIT_BOARD, BLUE_HOUSE_RECOMAND_MAIN})
    @Retention( RetentionPolicy.SOURCE )

    @interface BoardDefinition{}


    interface OnFinishedListener{
        void onFinished(final boolean isError, String result, Bundle responseData);
    }

    void init(AppCompatActivity activity);

    void loadBoard(@BoardDefinition  int boardKind);
    void initFragment(Fragment fragment);
}
