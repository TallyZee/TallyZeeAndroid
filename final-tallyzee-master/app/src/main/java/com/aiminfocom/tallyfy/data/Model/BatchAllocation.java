package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.Date;
@Entity(tableName = "BatchAllocation")
public class BatchAllocation  implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "gowdownName")
   private String gowdownName;
    @ColumnInfo(name = "orderNumber")
    private String orderNumber;
    @ColumnInfo(name = "batchName")
    private String batchName;
    @ColumnInfo(name = "amount")
    private  String amount;
    @ColumnInfo(name = "actualQty")
    private  String actualQty;
    @ColumnInfo(name = "billQty")
    private String billQty;
    @ColumnInfo(name = "orderDueDate")
    private  String orderDueDate;

public BatchAllocation()
{

}


    @Override
    public String toString() {
        return "BatchAllocation{" +
                "gowdownName='" + gowdownName + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", batchName='" + batchName + '\'' +
                ", amount='" + amount + '\'' +
                ", actualQty='" + actualQty + '\'' +
                ", billQty='" + billQty + '\'' +
                ", orderDueDate='" + orderDueDate + '\'' +
                '}';
    }

    public BatchAllocation(int id, String gowdownName, String orderNumber, String batchName, String amount, String actualQty, String billQty, String orderDueDate) {
        this.id = id;
        this.gowdownName = gowdownName;
        this.orderNumber = orderNumber;
        this.batchName = batchName;
        this.amount = amount;
        this.actualQty = actualQty;
        this.billQty = billQty;
        this.orderDueDate = orderDueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected BatchAllocation(Parcel in) {
        gowdownName = in.readString();
        orderNumber = in.readString();
        batchName = in.readString();
        amount = in.readString();
        actualQty = in.readString();
        billQty = in.readString();
        orderDueDate = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(gowdownName);
        dest.writeString(orderNumber);
        dest.writeString(batchName);
        dest.writeString(amount);
        dest.writeString(actualQty);
        dest.writeString(billQty);
        dest.writeString(orderDueDate);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<BatchAllocation> CREATOR = new Creator<BatchAllocation>() {
        @Override
        public BatchAllocation createFromParcel(Parcel in) {
            return new BatchAllocation(in);
        }

        @Override
        public BatchAllocation[] newArray(int size) {
            return new BatchAllocation[size];
        }
    };


    public String getGowdownName() {
        return gowdownName;
    }

    public void setGowdownName(String gowdownName) {
        this.gowdownName = gowdownName;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getBatchName() {
        return batchName;
    }

    public void setBatchName(String batchName) {
        this.batchName = batchName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getActualQty() {
        return actualQty;
    }

    public void setActualQty(String actualQty) {
        this.actualQty = actualQty;
    }

    public String getBillQty() {
        return billQty;
    }

    public void setBillQty(String billQty) {
        this.billQty = billQty;
    }

    public String getOrderDueDate() {
        return orderDueDate;
    }

    public void setOrderDueDate(String orderDueDate) {
        this.orderDueDate = orderDueDate;
    }
}
