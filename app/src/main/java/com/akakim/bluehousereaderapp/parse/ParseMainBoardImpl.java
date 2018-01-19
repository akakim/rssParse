package com.akakim.bluehousereaderapp.parse;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.akakim.bluehousereaderapp.MainJavaActivity;
import com.akakim.bluehousereaderapp.parse.parsetask.BlueHouseTask;
import com.akakim.bluehousereaderapp.ui.activity.*;

import java.util.Iterator;
import java.util.Map;


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
    public void init() {

    }

    @Override
    public void loadBoard(@BoardDefinition int boardKind) {

        Thread t;
        switch (boardKind){
            case ParseMainBoardInteractor.BLUE_HOUSE_INIT_BOARD:
                t = new Thread( new BlueHouseTask(onFinishedListener) );
                t.start();
                break;
            case ParseMainBoardInteractor.BLUE_HOUSE_RECOMAND_MAIN:
                t = new Thread( new BlueHouseTask(onFinishedListener) );
                t.start();
                break;
        }
    }

    @Override
    public void loadBoard(String url) {
        new Thread( new BlueHouseTask( url, onFinishedListener ));
    }

    @Override
    public void loadBoard(String url, Map<String, String> getParameter) {
        
       Iterator<String> iterator =  getParameter.keySet().iterator();

       StringBuilder builder = new StringBuilder();

       builder.append("?");
       for(String key : getParameter.keySet() ){

           builder.append(key+"=" +getParameter.get(key)+"&");
       }


       builder.substring( builder.length(),1);

        new Thread( new BlueHouseTask( url + builder.toString(), onFinishedListener )).start();

    }

    @Override
    public void initFragment(Fragment fragment) {

    }
}
