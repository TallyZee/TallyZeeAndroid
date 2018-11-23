package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.aiminfocom.tallyfy.data.db.RoomDbHelper.DataConverter;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "VoucherInventories")
public class VoucherInventories implements Parcelable,Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;
    @ColumnInfo(name = "actualyQty")
    public String actualQty;
    @ColumnInfo(name = "amount")
    public String amount;
    @ColumnInfo(name = "basicUserDesc")
    public String basicUserDesc;
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "batchAllocations")
    public List<BatchAllocation> batchAllocationList;
    @ColumnInfo(name = "edqty")
    public String edqty;
    @ColumnInfo(name = "rate")
    public String rate;
    @ColumnInfo(name = "stockItemName")
    public String stockItemName;


    public VoucherInventories() {

    }

    public VoucherInventories(String actualyQty, String amount, String basicUserDesc, List<BatchAllocation> batchAllocationList, String edqty, String rate, String stockItemName) {
        this.actualQty = actualyQty;
        this.amount = amount;
        this.basicUserDesc = basicUserDesc;
        this.batchAllocationList = batchAllocationList;
        this.edqty = edqty;
        this.rate = rate;
        this.stockItemName = stockItemName;
    }

    @Override
    public String toString() {
        return "VoucherInventories{" +
                "id=" + id +
                ", actualQty='" + actualQty + '\'' +
                ", amount='" + amount + '\'' +
                ", basicUserDesc='" + basicUserDesc + '\'' +
                ", batchAllocationList=" + batchAllocationList +
                ", edqty='" + edqty + '\'' +
                ", rate='" + rate + '\'' +
                ", stockItemName='" + stockItemName + '\'' +
                '}';
    }

    protected VoucherInventories(Parcel in) {
        actualQty = in.readString();
        amount = in.readString();
        basicUserDesc = in.readString();
        batchAllocationList = in.createTypedArrayList(BatchAllocation.CREATOR);
    }


    public static final Creator<VoucherInventories> CREATOR = new Creator<VoucherInventories>() {
        @Override
        public VoucherInventories createFromParcel(Parcel in) {
            return new VoucherInventories(in);
        }

        @Override
        public VoucherInventories[] newArray(int size) {
            return new VoucherInventories[size];
        }
    };

    public String getActualyQty() {
        return actualQty;
    }

    public void setActualyQty(String actualyQty) {
        this.actualQty = actualyQty;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getBasicUserDesc() {
        return basicUserDesc;
    }

    public void setBasicUserDesc(String basicUserDesc) {
        this.basicUserDesc = basicUserDesc;
    }

    public List<BatchAllocation> getBatchAllocations() {
        return batchAllocationList;
    }

    public void setBatchAllocations(List<BatchAllocation> batchAllocations) {
        this.batchAllocationList = batchAllocations;
    }

    public String getEdqty() {
        return edqty;
    }

    public static Creator<VoucherInventories> getCREATOR() {
        return CREATOR;
    }

    public void setEdqty(String edqty) {
        this.edqty = edqty;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getStockItemName() {
        return stockItemName;
    }

    public void setStockItemName(String stockItemName) {
        this.stockItemName = stockItemName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(actualQty);
        dest.writeString(amount);
        dest.writeString(basicUserDesc);
        dest.writeTypedList(batchAllocationList);
        dest.writeString(edqty);
        dest.writeString(rate);
        dest.writeString(stockItemName);
    }
}
