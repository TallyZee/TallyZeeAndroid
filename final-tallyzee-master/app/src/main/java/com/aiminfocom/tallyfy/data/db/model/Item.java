package com.aiminfocom.tallyfy.data.db.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Item implements Parcelable{
    String billAmount;
    String billCreditPeriod;

    public Item(String billAmount, String billCreditPeriod, String billType, String billName) {
        this.billAmount = billAmount;
        this.billCreditPeriod = billCreditPeriod;
        this.billType = billType;
        this.billName = billName;
    }

    String billType;

    protected Item(Parcel in) {
        billAmount = in.readString();
        billCreditPeriod = in.readString();
        billType = in.readString();
        billName = in.readString();
    }

    public static final Creator<Item> CREATOR = new Creator<Item>() {
        @Override
        public Item createFromParcel(Parcel in) {
            return new Item(in);
        }

        @Override
        public Item[] newArray(int size) {
            return new Item[size];
        }
    };

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillCreditPeriod() {
        return billCreditPeriod;
    }

    public void setBillCreditPeriod(String billCreditPeriod) {
        this.billCreditPeriod = billCreditPeriod;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    String billName;

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(billAmount);
        parcel.writeString(billCreditPeriod);
        parcel.writeString(billType);
        parcel.writeString(billName);
    }
}
