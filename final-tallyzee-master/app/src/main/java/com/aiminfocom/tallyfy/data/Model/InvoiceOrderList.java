package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
@Entity(tableName = "InvoiceOrderList")
public class InvoiceOrderList implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "basicOrderRate")
    private  String basicOrderRate;
    @ColumnInfo(name = "orderType")
    private String orderType;
    @ColumnInfo(name = "basicPurchaseOrderNumber")
    private String basicPurchaseOrderNumber;

    protected InvoiceOrderList(Parcel in) {
        basicOrderRate = in.readString();
        orderType = in.readString();
        basicPurchaseOrderNumber = in.readString();
    }

    public static final Creator<InvoiceOrderList> CREATOR = new Creator<InvoiceOrderList>() {
        @Override
        public InvoiceOrderList createFromParcel(Parcel in) {
            return new InvoiceOrderList(in);
        }

        @Override
        public InvoiceOrderList[] newArray(int size) {
            return new InvoiceOrderList[size];
        }
    };

    public String getBasicOrderRate() {
        return basicOrderRate;
    }

    public void setBasicOrderRate(String basicOrderRate) {
        this.basicOrderRate = basicOrderRate;
    }

    public String getOrderType() {
        return orderType;
    }

    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }

    public String getBasicPurchaseOrderNumber() {
        return basicPurchaseOrderNumber;
    }

    public void setBasicPurchaseOrderNumber(String basicPurchaseOrderNumber) {
        this.basicPurchaseOrderNumber = basicPurchaseOrderNumber;
    }

    public InvoiceOrderList(String basicOrderRate, String orderType, String basicPurchaseOrderNumber) {

        this.basicOrderRate = basicOrderRate;
        this.orderType = orderType;
        this.basicPurchaseOrderNumber = basicPurchaseOrderNumber;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(basicOrderRate);
        dest.writeString(orderType);
        dest.writeString(basicPurchaseOrderNumber);
    }
}
