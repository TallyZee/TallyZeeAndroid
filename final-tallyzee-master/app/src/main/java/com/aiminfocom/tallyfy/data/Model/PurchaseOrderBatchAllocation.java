package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
@Entity(tableName = "PurchaseOrderBatchAllocation")
public class PurchaseOrderBatchAllocation implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "gowdownName")
   private String gowdownName;
    @ColumnInfo(name = "orderNumber")
    private String orderNumber;
    @ColumnInfo(name = "batchName")
    private String batchName;
    @ColumnInfo(name = "amount")
    private String amount;
    @ColumnInfo(name = "actualQty")
    private String actualQty;
    @ColumnInfo(name = "billQty")
    private  String billQty;
    @ColumnInfo(name = "orderDueDate")
    private  String orderDueDate;

    protected PurchaseOrderBatchAllocation(Parcel in) {
        gowdownName = in.readString();
        orderNumber = in.readString();
        batchName = in.readString();
        amount = in.readString();
        actualQty = in.readString();
        billQty = in.readString();
        orderDueDate = in.readString();
    }

    public PurchaseOrderBatchAllocation()
    {

    }

    public static final Creator<PurchaseOrderBatchAllocation> CREATOR = new Creator<PurchaseOrderBatchAllocation>() {
        @Override
        public PurchaseOrderBatchAllocation createFromParcel(Parcel in) {
            return new PurchaseOrderBatchAllocation(in);
        }

        @Override
        public PurchaseOrderBatchAllocation[] newArray(int size) {
            return new PurchaseOrderBatchAllocation[size];
        }
    };

    public String getGowdownName() {
        return gowdownName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "PurchaseOrderBatchAllocation{" +
                "id=" + id +
                ", gowdownName='" + gowdownName + '\'' +
                ", orderNumber='" + orderNumber + '\'' +
                ", batchName='" + batchName + '\'' +
                ", amount='" + amount + '\'' +
                ", actualQty='" + actualQty + '\'' +
                ", billQty='" + billQty + '\'' +
                ", orderDueDate='" + orderDueDate + '\'' +
                '}';
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

    public PurchaseOrderBatchAllocation(String gowdownName, String orderNumber, String batchName, String amount, String actualQty, String billQty, String orderDueDate) {
        this.gowdownName = gowdownName;
        this.orderNumber = orderNumber;
        this.batchName = batchName;
        this.amount = amount;
        this.actualQty = actualQty;
        this.billQty = billQty;
        this.orderDueDate = orderDueDate;
    }

    @Override
    public int describeContents() {
        return 0;
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
}
