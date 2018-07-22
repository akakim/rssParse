package com.akakim.bluehousereaderapp.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RyoRyeong Kim on 2018-01-19.
 */

public class FeedData {


    public static final String FEED_TYPE_ITEM ="feedType";

    String feedName;
    String type;

    Class<?> aClass;


    public FeedData() {
    }

    public FeedData(String feedName, String type, Class<?> aClass) {
        this.feedName   = feedName;
        this.type       = type;
        this.aClass     = aClass;

    }


    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }


    public Class<?> getaClass() {
        return aClass;
    }

    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }



}
