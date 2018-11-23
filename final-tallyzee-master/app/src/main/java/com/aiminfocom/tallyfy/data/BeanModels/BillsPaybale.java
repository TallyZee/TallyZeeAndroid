package com.aiminfocom.tallyfy.data.BeanModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by GulshanPC on 30/06/2018.
 */
@Entity(tableName="BillsPaybale")
public class BillsPaybale implements Parcelable {
    @NonNull
    public String getBillref() {
        return billref;
    }

    public void setBillref(@NonNull String billref) {
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


    public String getBillCl() {
        return billCl;
    }

    public void setBillCl(String billCl) {
        this.billCl = billCl;
    }


    public static Creator<BillsPaybale> getCREATOR() {
        return CREATOR;
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


    @SerializedName("BILLCL")
    @ColumnInfo(name = "BILLCL")
    public String billCl;



//    public String getBillDueOn() {
//        return billDueOn;
//    }
//
//    public void setBillDueOn(String billDueOn) {
//        this.billDueOn = billDueOn;
//    }
//
//    public String getBillOverDue() {
//        return billOverDue;
//    }
//
//    public void setBillOverDue(String billOverDue) {
//        this.billOverDue = billOverDue;
//    }

//    @SerializedName("OUTDUEON")
//    @ColumnInfo(name = "OUTDUEON")
//    public String billDueOn;
//
//    @SerializedName("OVERDUEDAYS")
//    @ColumnInfo(name = "OVERDUEDAYS")
//    public String billOverDue;

public BillsPaybale(String billref,String billDate,String billParty,String billCl)
{
    this.billref=billref;
    this.billParty=billParty;
    this.billDate=billDate;
    this.billCl=billCl;
//    this.billDueOn=billDueOn;
//    this.billOverDue=billOverDue;
}

    protected BillsPaybale(Parcel in) {
    this.billCl=in.readString();
    this.billDate=in.readString();
    this.billParty=in.readString();
    this.billref=in.readString();
//    this.billDueOn=in.readString();
//    this.billOverDue=in.readString();
    }

    public static final Creator<BillsPaybale> CREATOR = new Creator<BillsPaybale>() {
        @Override
        public BillsPaybale createFromParcel(Parcel in) {
            return new BillsPaybale(in);
        }

        @Override
        public BillsPaybale[] newArray(int size) {
            return new BillsPaybale[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.billref);
        dest.writeString(this.billCl);
        dest.writeString(this.billDate);
        dest.writeString(this.billParty);
//        dest.writeString(this.billDueOn);
//        dest.writeString(this.billOverDue);
    }
}
