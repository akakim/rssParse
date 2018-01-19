package com.akakim.bluehousereaderapp.data;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by RyoRyeong Kim on 2018-01-19.
 */

public class FeedData {

    public static final String FEED_ITEM_KEY = "feedItem";
    String feedName;
    String feedURL;

    Class<?> aClass;


    public FeedData() {
    }

    public FeedData(String feedName, String feedURL, Class<?> aClass) {
        this.feedName = feedName;
        this.feedURL = feedURL;
        this.aClass = aClass;
    }


    public String getFeedName() {
        return feedName;
    }

    public void setFeedName(String feedName) {
        this.feedName = feedName;
    }

    public String getFeedURL() {
        return feedURL;
    }

    public void setFeedURL(String feedURL) {
        this.feedURL = feedURL;
    }


    public Class<?> getaClass() {
        return aClass;
    }

    public void setaClass(Class<?> aClass) {
        this.aClass = aClass;
    }

    @Override
    public String toString() {
        return "FeedData{" +
                "feedName='" + feedName + '\'' +
                ", feedURL='" + feedURL + '\'' +
                ", aClass=" + aClass.getSimpleName() +
                '}';
    }
}
