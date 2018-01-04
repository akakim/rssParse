package com.akakim.bluehousereaderapp.parse;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.akakim.bluehousereaderapp.MainJavaActivity;
import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.data.Constants;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;

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
            boolean isBestBoardExist = false;
            boolean isReadyAnswerExist = false;
            final StringBuilder builder = new StringBuilder();

            final Bundle bundles = new Bundle();
            ArrayList<BoardData> boardDataArrayList = new ArrayList<BoardData>();
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


                            BoardData bestBoard = new BoardData();


                            bestBoard.setBoardTag(BoardData.BEST_BOARD_TAG);
                            bestBoard.setTitle( ele.select("h5").text());
                            bestBoard.setThumbnailContent( ele.select("p").text() );

                            int k = 0;
                            for ( Element aElement : ele.select("span")){
                                Log.d(getClass().getSimpleName(),"k - " + k + " : " + aElement.text() );


                                switch ( k ){
                                    case 0 :
                                        bestBoard.setAuthor( aElement.text() );
                                        break;
                                    case 1:
                                        bestBoard.setNumberOfJoinPeople( aElement.text());
                                        break;
                                    case 2:
                                        bestBoard.setTerm( aElement.text());
                                        break;
                                }

                                k++;

                            }

                            Elements linkEle = ele.select("a[href]");
                            bestBoard.setLink(linkEle.attr("href"));
                            boardDataArrayList.add( bestBoard);
                            builder.append("title " + ele.select("h5").text());
                            builder.append("title " + ele.select("p").text());
                            isBestBoardExist = true;
                            break;
                        case "":
                            break;
                        default:
                            break;
                    }
                }

                bundles.putBoolean(BoardData.BEST_BOARD_TAG,isBestBoardExist );
                bundles.putBoolean(BoardData.READY_ANSWER_BOARD_TAG,isReadyAnswerExist );
                bundles.putParcelableArrayList(BoardData.BOARD_ITEMS_KEY,boardDataArrayList);

//                ArrayList<BoardData> test
                onFinishedListener.onFinished(false, builder.toString(), bundles);
            } catch (MalformedInputException e){
                builder.append("MalformedInputException : " + e.getMessage());

                e.printStackTrace();
                onFinishedListener.onFinished(true,builder.toString(),bundles);
            } catch (IOException e ){
                builder.append("IOException : " + e.getMessage());
                e.printStackTrace();
                onFinishedListener.onFinished(true,builder.toString(),bundles);
            }catch (Exception e){
                builder.append("Exception : " + e.getMessage());
                e.printStackTrace();
                onFinishedListener.onFinished(true,builder.toString(),bundles);
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
