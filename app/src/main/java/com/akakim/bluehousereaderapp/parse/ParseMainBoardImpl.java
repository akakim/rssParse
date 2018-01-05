package com.akakim.bluehousereaderapp.parse;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.akakim.bluehousereaderapp.MainJavaActivity;
import com.akakim.bluehousereaderapp.parse.parsetask.BlueHouseTask;


/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 */

public class ParseMainBoardImpl implements ParseMainBoardInteractor {

    OnFinishedListener onFinishedListener;

    public ParseMainBoardImpl(OnFinishedListener onFinishedListener){
        this.onFinishedListener =onFinishedListener;
    }
    @Override
    public void init(AppCompatActivity activity) {
        if (activity == null){
            return;
        }else if( activity instanceof MainJavaActivity ){
            Thread t = new Thread( new BlueHouseTask(onFinishedListener) );
            t.start();
        }else {
            Log.e(getClass().getSimpleName(),"error undefinded");
        }
    }

    @Override
    public void initFragment(Fragment fragment) {

    }
}
