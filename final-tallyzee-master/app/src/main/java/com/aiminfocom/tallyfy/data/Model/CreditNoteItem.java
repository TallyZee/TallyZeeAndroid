package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
@Entity(tableName = "CreditNoteItem")
public class CreditNoteItem implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "billName")
    private String billName;
    @ColumnInfo(name = "billAmount")
    private String billAmount;
    @ColumnInfo(name = "billType")
    private String billType;

    public CreditNoteItem() {
    }

    protected CreditNoteItem(Parcel in) {
        billName = in.readString();
        billAmount = in.readString();
        billType = in.readString();
    }

    public static final Creator<CreditNoteItem> CREATOR = new Creator<CreditNoteItem>() {
        @Override
        public CreditNoteItem createFromParcel(Parcel in) {
            return new CreditNoteItem(in);
        }

        @Override
        public CreditNoteItem[] newArray(int size) {
            return new CreditNoteItem[size];
        }
    };

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public CreditNoteItem(String billName, String billAmount, String billType) {
        this.billName = billName;
        this.billAmount = billAmount;
        this.billType = billType;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(billName);
        dest.writeString(billAmount);
        dest.writeString(billType);
    }
}
