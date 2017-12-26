package com.akakim.bluehousereaderapp.parse;

import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;

import com.akakim.bluehousereaderapp.MainJavaActivity;
import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.data.Constants;

import org.json.JSONObject;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.MalformedInputException;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2017-12-23
 * @since 0.0.1
 */

public class ParseMainBoardImpl implements ParseMainBoardInteractor {

    OnFinishedListener onFinishedListener;

    Runnable fullBoardTask;
    Runnable dashBoardTask;

    public ParseMainBoardImpl(OnFinishedListener onFinishedListener){
        this.onFinishedListener =onFinishedListener;

        fullBoardTask = () -> {
            final StringBuilder builder = new StringBuilder();

            final JSONObject jsonObject = new JSONObject();
            try{

                Connection connection = Jsoup.connect(Constants.PUBLIC_OPINION_BASE_URL);
                connection.timeout(5000);
                Document doc = connection.get();
                Elements classDivs = doc.select("div[class]");

                int i = 0;
                for( Element ele : classDivs ){

                    i++;

                    switch (ele.attr("class")){
                        case "cspb_info text":

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
        };
    }
    @Override
    public void init(AppCompatActivity activity) {
        if (activity == null){
            return;
        }else if( activity instanceof MainJavaActivity ){
            Thread t = new Thread( fullBoardTask );
            t.start();
        }
    }

    @Override
    public void initFragment(Fragment fragment) {

    }
}
