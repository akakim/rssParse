package com.akakim.bluehousereaderapp.parse.parsetask;

import android.os.Bundle;
import android.util.Log;

import com.akakim.bluehousereaderapp.BuildConfig;
import com.akakim.bluehousereaderapp.data.BoardData;
import com.akakim.bluehousereaderapp.data.Constants;
import com.akakim.bluehousereaderapp.parse.ParseMainBoardInteractor;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.nio.charset.MalformedInputException;
import java.util.ArrayList;

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

                            if( BuildConfig.DEBUG ) {
                                Log.d(getClass().getSimpleName(), "k - " + k + " : " + aElement.text());
                            }

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
                    case "board text":

                        if("답변 대기 중인 청원".equals( ele.select("h4").text())) {
                            Log.d(getClass().getSimpleName(),"답변 대기 중은 결정안됨 ");
                            break;
                        }else if("청원 목록".equals( ele.select("h4").text())){

                            Elements boarItems = ele.select("div[class]");


                            ArrayList<BoardData> middleDatas = new ArrayList<>();

                            BoardData boardData = new BoardData();

                            for( Element boardItem : boarItems ){

                                Elements numberEles = boardItem.getElementsByClass("bl_no");


                                Elements categoryEles = boardItem.getElementsByClass("bl_category cs");
                                Elements titleEles = boardItem.getElementsByClass("bl_category cs");
                                Elements termEles = boardItem.getElementsByClass("bl_category cs");
                                Elements peopleEles = boardItem.getElementsByClass("bl_category cs");



                                boardData.setBoardTag( BoardData.BOARD_ITEMS_KEY);
                                boardData.setBoardIdx( numberEles.text());
                                boardData.setCategory( categoryEles.text());
                                boardData.setTitle( titleEles.text());
                                boardData.setTerm( termEles.text());
                                boardData.setNumberOfJoinPeople( peopleEles.text());

                                if( BuildConfig.DEBUG ) {
                                    Log.d(getClass().getSimpleName(), "신규 생성된 Data " + boardData.toString());
                                }
                                boardDataArrayList.add(boardData);
                            }

                        } else {
                            Log.e(getClass().getSimpleName(),"알 수 없는 에러 발생 in board_text");
                        }
//                        BoardData commonRecord

                        break;
                    default:
                        Log.e(getClass().getSimpleName(),"알 수 없는 에러 발생 in default switch");
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
    }
}
