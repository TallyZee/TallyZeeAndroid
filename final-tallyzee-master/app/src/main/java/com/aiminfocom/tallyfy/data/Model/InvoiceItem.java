package com.aiminfocom.tallyfy.data.Model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class InvoiceItem  implements Serializable,Parcelable{
    private  String basicOrderDate;
    private String orderType;
    private String basicPurchaseOrderNo;
    public InvoiceItem(String basicOrderDate, String orderType, String basicPurchaseOrderNo) {
        super();
        this.basicOrderDate = basicOrderDate;
        this.orderType = orderType;
        this.basicPurchaseOrderNo = basicPurchaseOrderNo;
    }

    protected InvoiceItem(Parcel in) {
        basicOrderDate = in.readString();
        orderType = in.readString();
        basicPurchaseOrderNo = in.readString();
    }

    public static final Creator<InvoiceItem> CREATOR = new Creator<InvoiceItem>() {
        @Override
        public InvoiceItem createFromParcel(Parcel in) {
            return new InvoiceItem(in);
        }

        @Override
        public InvoiceItem[] newArray(int size) {
            return new InvoiceItem[size];
        }
    };

    public String getBasicOrderDate() {
        return basicOrderDate;
    }
    public void setBasicOrderDate(String basicOrderDate) {
        this.basicOrderDate = basicOrderDate;
    }
    public String getOrderType() {
        return orderType;
    }
    public void setOrderType(String orderType) {
        this.orderType = orderType;
    }
    public String getBasicPurchaseOrderNo() {
        return basicPurchaseOrderNo;
    }
    public void setBasicPurchaseOrderNo(String basicPurchaseOrderNo) {
        this.basicPurchaseOrderNo = basicPurchaseOrderNo;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(basicOrderDate);
        dest.writeString(orderType);
        dest.writeString(basicPurchaseOrderNo);
    }
}
