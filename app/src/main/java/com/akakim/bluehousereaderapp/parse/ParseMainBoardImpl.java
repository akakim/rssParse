package com.akakim.bluehousereaderapp.parse;

import android.net.Uri;
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
 *
 * 실제로 파싱을하는 구현체.
 * 만약, Thread가 아닌 별도의 작업
 */

public class ParseMainBoardImpl implements ParseMainBoardInteractor {

    OnFinishedListener onFinishedListener;

    public ParseMainBoardImpl(OnFinishedListener onFinishedListener){
        this.onFinishedListener =onFinishedListener;
    }
    @Override
    public void init() {

    }

    /**
     * issue : Thread의 run 과 start는 다른 것이므로 start로 처리를한다.
     * 기본적인 안드로이드의 조건이다. 메인스레드에서 네트워크 작업은 하지 않는다.
     * 백그라운드에서 돌린다.
     * @param url
     */
    @Override
    public void loadBoard(String url) {
        new Thread( new BlueHouseTask( url, onFinishedListener )).start();
    }

    @Override
    public void loadBoard(Uri uri) {
        new Thread( new BlueHouseTask( uri , onFinishedListener )).start();
    }

    @Override
    public void loadNextPage( Uri uri ) {
        new Thread( new BlueHouseTask( uri , onFinishedListener )).start();
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

}
