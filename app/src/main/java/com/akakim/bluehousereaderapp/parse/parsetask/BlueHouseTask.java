package com.akakim.bluehousereaderapp.parse.parsetask;

import android.os.Bundle;

import com.akakim.bluehousereaderapp.data.Constants;
import com.akakim.bluehousereaderapp.parse.ParseMainBoardInteractor;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

/**
 * Created by RyoRyeong Kim on 2018-01-05.
 */

public class BlueHouseTask implements Runnable  {

    ParseMainBoardInteractor.OnFinishedListener onFinishedListener;

    public BlueHouseTask(ParseMainBoardInteractor.OnFinishedListener onFinishedListener) {
        this.onFinishedListener = onFinishedListener;
    }

    @Override
    public void run() {
        final StringBuilder builder = new StringBuilder();

        final Bundle jsonObject = new Bundle();
        try{

            Connection connection = Jsoup.connect(Constants.PUBLIC_OPINION_BASE_URL);
            connection.timeout(6000);
            Document doc = connection.get();
            Elements classDivs = doc.select("div[class]");

            int i = 0;
            for( Element ele : classDivs ){

                i++;

                switch (ele.attr("class")){
                    case "cspb_info text":


//                            jsonObject.putStringArrayList("ss");
                        builder.append("title " + ele.select("h5").text());
                        builder.append("title " + ele.select("p").text());
                        break;
                    case "":
                        break;
                    default:
                        break;
                }
            }

            onFinishedListener.onFinished(false, builder.toString(), jsonObject);
        } catch (MalformedInputException e){
            builder.append("MalformedInputException : " + e.getMessage());

            e.printStackTrace();
            onFinishedListener.onFinished(true,builder.toString(),jsonObject);
        } catch (IOException e ){
            builder.append("IOException : " + e.getMessage());
            e.printStackTrace();
            onFinishedListener.onFinished(true,builder.toString(),jsonObject);
        }catch (Exception e){
            builder.append("Exception : " + e.getMessage());
            e.printStackTrace();
            onFinishedListener.onFinished(true,builder.toString(),jsonObject);
        }
    }
}
