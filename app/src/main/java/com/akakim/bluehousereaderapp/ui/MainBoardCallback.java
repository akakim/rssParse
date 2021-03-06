package com.akakim.bluehousereaderapp.ui;

import android.os.Bundle;

import com.akakim.bluehousereaderapp.data.BoardData;

import org.json.JSONObject;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 */

public interface MainBoardCallback {

    void onResumeRefresh();
    void showProgress(String message);
    void responseFailed(String result);
    void responseSuccess(Bundle responseData);

    void setUI(Runnable runnable);


    void showMessage(String msg);
    void showAlert();

}
