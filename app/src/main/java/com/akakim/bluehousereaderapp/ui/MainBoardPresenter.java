package com.akakim.bluehousereaderapp.ui;

import com.akakim.bluehousereaderapp.parse.ParseMainBoardInteractor;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 */

public interface MainBoardPresenter {
    void initContent();
    void initContent(@ParseMainBoardInteractor.BoardDefinition int def);
    void initContent(String url);
}
