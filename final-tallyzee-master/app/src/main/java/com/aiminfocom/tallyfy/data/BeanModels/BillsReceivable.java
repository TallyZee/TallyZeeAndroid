package com.aiminfocom.tallyfy.data.BeanModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GulshanPC on 30/06/2018.
 */
public class BillsReceivable implements Parcelable {


    public String getBillref() {
        return billref;
    }

    public void setBillref(String billref) {
        this.billref = billref;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillParty() {
        return billParty;
    }

    public void setBillParty(String billParty) {
        this.billParty = billParty;
    }

    public String getBillDue() {
        return billDue;
    }

    public void setBillDue(String billDue) {
        this.billDue = billDue;
    }

    public String getBillCl() {
        return billCl;
    }

    public void setBillCl(String billCl) {
        this.billCl = billCl;
    }

    public String getBillOverDue() {
        return billOverDue;
    }

    public void setBillOverDue(String billOverDue) {
        this.billOverDue = billOverDue;
    }

    @PrimaryKey
    @SerializedName("BILLREF")
    @ColumnInfo(name = "BILLREF")
    @NonNull
    public String billref;

    @SerializedName("BILLDATE")
    @ColumnInfo(name = "BILLDATE")
    public String billDate;

    @SerializedName("BILLPARTY")
    @ColumnInfo(name = "BILLPARTY")
    public String billParty;

    @SerializedName("BILLDUE")
    @ColumnInfo(name = "BILLDUE")
    public String billDue;

    @SerializedName("BILLCL")
    @ColumnInfo(name = "BILLCL")
    public String billCl;

    @SerializedName("BILLOVERDUE")
    @ColumnInfo(name = "BILLOVERDUE")
    public String billOverDue;


    public BillsReceivable(String billref, String billDate, String billParty, String billDue, String billCl, String billOverDue)
    {
    this.billref=billref;
    this.billDate=billDate;
    this.billParty=billParty;
    this.billDue=billDue;
    this.billCl=billCl;
    this.billOverDue=billOverDue;
    }

    public BillsReceivable(Parcel in) {
    }

    public static final Creator<BillsReceivable> CREATOR = new Creator<BillsReceivable>() {
        @Override
        public BillsReceivable createFromParcel(Parcel in) {
            return new BillsReceivable(in);
        }

        @Override
        public BillsReceivable[] newArray(int size) {
            return new BillsReceivable[size];
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
