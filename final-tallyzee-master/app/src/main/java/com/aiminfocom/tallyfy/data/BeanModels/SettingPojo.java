package com.aiminfocom.tallyfy.data.BeanModels;

import android.os.Parcel;
import android.os.Parcelable;

public class SettingPojo implements Parcelable{


    private int image;
    private String title;
    private String info;


    public SettingPojo(int image, String title, String info) {
        this.image = image;
        this.title = title;
        this.info = info;
    }

    protected SettingPojo(Parcel in) {
        image = in.readInt();
        title = in.readString();
        info = in.readString();
    }

    public static final Creator<SettingPojo> CREATOR = new Creator<SettingPojo>() {
        @Override
        public SettingPojo createFromParcel(Parcel in) {
            return new SettingPojo(in);
        }

        @Override
        public SettingPojo[] newArray(int size) {
            return new SettingPojo[size];
        }
    };

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(image);
        dest.writeString(title);
        dest.writeString(info);
    }
}
