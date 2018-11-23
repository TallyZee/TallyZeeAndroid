package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
@Entity(tableName = "Item")
public class Item  implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "billName")
    private  String billName;
    @ColumnInfo(name = "billAmount")
    private String billAmount;
    @ColumnInfo(name = "billType")
    private String billType;
    @ColumnInfo(name = "billCreditPeriod")
    private String billCreditPeriod;


    public Item()
    {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Item{" +
                "billName='" + billName + '\'' +
                ", billAmount='" + billAmount + '\'' +
                ", billType='" + billType + '\'' +
                ", billCreditPeriod='" + billCreditPeriod + '\'' +
                ", billCreditPeriod='" + billCreditPeriod + '\'' +
                '}';
    }

    public Item(int id, String billName, String billAmount, String billType, String billCreditPeriod) {
        this.id = id;
        this.billName = billName;
        this.billAmount = billAmount;
        this.billType = billType;
        this.billCreditPeriod = billCreditPeriod;
    }

    protected Item(Parcel in) {
        billName = in.readString();
        billAmount = in.readString();
        billType = in.readString();
        billCreditPeriod = in.readString();
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

    public String getBillCreditPeriod() {
        return billCreditPeriod;
    }

    public void setBillCreditPeriod(String billCreditPeriod) {
        this.billCreditPeriod = billCreditPeriod;
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
        dest.writeString(billCreditPeriod);
    }
}
