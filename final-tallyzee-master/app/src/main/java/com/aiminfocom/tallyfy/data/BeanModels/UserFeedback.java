package com.aiminfocom.tallyfy.data.BeanModels;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class UserFeedback implements Serializable,Parcelable {


    private String name;
    private String rating;

    public UserFeedback(String name, String rating) {
        this.name = name;
        this.rating = rating;
    }

    protected UserFeedback(Parcel in) {
        name = in.readString();
        rating = in.readString();
    }

    public static final Creator<UserFeedback> CREATOR = new Creator<UserFeedback>() {
        @Override
        public UserFeedback createFromParcel(Parcel in) {
            return new UserFeedback(in);
        }

        @Override
        public UserFeedback[] newArray(int size) {
            return new UserFeedback[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(rating);
    }
}