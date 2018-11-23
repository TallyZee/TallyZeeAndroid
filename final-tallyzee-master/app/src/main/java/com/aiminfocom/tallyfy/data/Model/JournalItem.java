package com.aiminfocom.tallyfy.data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class JournalItem implements Serializable,Parcelable {
    private String billref;
    private String billAmount;
    private String billName;

    protected JournalItem(Parcel in) {
        billref = in.readString();
        billAmount = in.readString();
        billName = in.readString();
    }

    public static final Creator<JournalItem> CREATOR = new Creator<JournalItem>() {
        @Override
        public JournalItem createFromParcel(Parcel in) {
            return new JournalItem(in);
        }

        @Override
        public JournalItem[] newArray(int size) {
            return new JournalItem[size];
        }
    };

    public String getBillref() {
        return billref;
    }

    public void setBillref(String billref) {
        this.billref = billref;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public JournalItem(String billref, String billAmount, String billName) {
        this.billref = billref;
        this.billAmount = billAmount;
        this.billName = billName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(billref);
        dest.writeString(billAmount);
        dest.writeString(billName);
    }
}
