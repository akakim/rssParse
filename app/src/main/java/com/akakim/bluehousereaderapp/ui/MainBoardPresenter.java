package com.akakim.bluehousereaderapp.ui;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;

import com.akakim.bluehousereaderapp.parse.ParseMainBoardInteractor;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 */

public interface MainBoardPresenter {

    String BLUE_HOUSE_BASIC_TYPE     = "blueHouseBasic";
    String BLUE_HOUSE_POPPULAR_TYPE  = "blueHousePop";
    String BLUE_HOUSE_CATEGORY_TYPE  = "blueHouseCate";
    String BLUE_HOUSE_ANSWER_TYPE    = "blueHouseAnswer";
    void initContent(String type);
    void updateContent( String type,String parameter, int page);

//    void initContent(String url);
//    void initContent(Uri uri );
//    void loadMoreContent ( Uri uri );
//
}
