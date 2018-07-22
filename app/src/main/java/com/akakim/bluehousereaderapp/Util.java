package com.akakim.bluehousereaderapp;

import android.net.Uri;
import android.support.annotation.NonNull;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * @author KIM
 * @version 0.0.1
 * @date 2018-01-20
 * @since 0.0.1
 */

public class Util  {

    public static String getURLFactory(String baseURL , Map<String,String> getParameter){


        StringBuilder builder = new StringBuilder(baseURL);


        builder.append("?");

        for( String key : getParameter.keySet()){
            builder.append(key +"=" +getParameter.get(key) +"&");
        }



        builder.deleteCharAt(builder.length() - 1);

        return builder.toString();
    }


    /**
     * page=2
     * @param getParameter
     * @return
     */
    public static Uri getBoardParmeterUri( @NonNull Map<String,String> getParameter){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("www1.president.go.kr");
        builder.path("petitions");

        Set<String> keySet = getParameter.keySet();
        Iterator<String> iter = keySet.iterator();
        while ( iter.hasNext()){

            String key = iter.next();
            String value = getParameter.get( key  );

            builder.appendQueryParameter( key, value );
        }

        return builder.build();
    }


    /**
     * c=0&only=1&page=4&order=1
     * @param getParameter
     * @return
     */
    public static Uri getBoardCategoryParmeterUri( @NonNull Map<String,String> getParameter){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("www1.president.go.kr");
        builder.path("petitions/category");

        Set<String> keySet = getParameter.keySet();
        Iterator<String> iter = keySet.iterator();
        while ( iter.hasNext()){

            String key = iter.next();
            String value = getParameter.get( key  );

            builder.appendQueryParameter( key, value );
        }

        return builder.build();
    }


    /**
     * page=2
     * @param getParameter
     * @return
     */
    public static Uri getBoardAnswerParmeterUri( @NonNull Map<String,String> getParameter){
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https");
        builder.authority("www1.president.go.kr");
        builder.path("petitions/category");

        Set<String> keySet = getParameter.keySet();
        Iterator<String> iter = keySet.iterator();
        while ( iter.hasNext()){

            String key = iter.next();
            String value = getParameter.get( key  );

            builder.appendQueryParameter( key, value );
        }

        return builder.build();
    }
}
