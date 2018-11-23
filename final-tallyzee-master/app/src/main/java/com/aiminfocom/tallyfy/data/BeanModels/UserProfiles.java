package com.aiminfocom.tallyfy.data.BeanModels;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class UserProfiles implements Parcelable,Serializable{



    public UserProfiles(String name,String email)
    {
        this.name=name;
        this.email=email;
    }

    private String name;
    private String email;


    protected UserProfiles(Parcel in) {
        name = in.readString();
        email = in.readString();
    }

    public static final Creator<UserProfiles> CREATOR = new Creator<UserProfiles>() {
        @Override
        public UserProfiles createFromParcel(Parcel in) {
            return new UserProfiles(in);
        }

        @Override
        public UserProfiles[] newArray(int size) {
            return new UserProfiles[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(email);
    }
}
