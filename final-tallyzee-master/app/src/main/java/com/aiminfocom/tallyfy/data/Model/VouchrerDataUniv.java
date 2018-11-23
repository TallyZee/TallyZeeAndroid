package com.aiminfocom.tallyfy.data.Model;

import android.os.Parcel;
import android.os.Parcelable;

public class VouchrerDataUniv implements Parcelable {
    public String getVoucherPartyName() {
        return voucherPartyName;
    }

    public void setVoucherPartyName(String voucherPartyName) {
        this.voucherPartyName = voucherPartyName;
    }

    public String getVoucherAmount() {
        return voucherAmount;
    }

    public void setVoucherAmount(String voucherAmount) {
        this.voucherAmount = voucherAmount;
    }

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public VouchrerDataUniv(String voucherPartyName, String voucherAmount, String voucherDate) {

        this.voucherPartyName = voucherPartyName;
        this.voucherAmount = voucherAmount;
        this.voucherDate = voucherDate;
    }

    String voucherPartyName;
    String voucherAmount;
    String voucherDate;


    protected VouchrerDataUniv(Parcel in) {
        voucherAmount = in.readString();
        voucherPartyName = in.readString();
        voucherDate = in.readString();
    }

    public static final Creator<VouchrerDataUniv> CREATOR = new Creator<VouchrerDataUniv>() {
        @Override
        public VouchrerDataUniv createFromParcel(Parcel in) {
            return new VouchrerDataUniv(in);
        }

        @Override
        public VouchrerDataUniv[] newArray(int size) {
            return new VouchrerDataUniv[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(voucherPartyName);
        dest.writeString(voucherAmount);
        dest.writeString(voucherDate);
    }
}
