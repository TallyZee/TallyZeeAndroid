package com.aiminfocom.tallyfy.data.BeanModels;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by GulshanPC on 30/06/2018.
 */

public class DayBook implements Parcelable {
    protected DayBook(Parcel in) {
    }

    public static final Creator<DayBook> CREATOR = new Creator<DayBook>() {
        @Override
        public DayBook createFromParcel(Parcel in) {
            return new DayBook(in);
        }

        @Override
        public DayBook[] newArray(int size) {
            return new DayBook[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
    }
}
