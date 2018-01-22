package com.akakim.bluehousereaderapp;

import java.util.Map;

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
}
