package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.aiminfocom.tallyfy.data.db.RoomDbHelper.DataConverter;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "Inventories")
public class Inventories implements Serializable,Parcelable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "stockItemName")
    private String stockItemName;
    @ColumnInfo(name = "basicUserDesc")
    private String basicUserDesc;
    @ColumnInfo(name = "rate")
    private String rate;
    @ColumnInfo(name = "amount")
    private  String amount;
    @ColumnInfo(name = "edqty")
    private String edqty;
    @ColumnInfo(name = "actualQty")
    private String actualQty;
    @ColumnInfo(name = "batchAllocationList")
    @TypeConverters(DataConverter.class)
    private List<BatchAllocation> batchAllocationList;


public Inventories()
{

}

    @Override
    public String toString() {
        return "Inventories{" +
                "stockItemName='" + stockItemName + '\'' +
                ", basicUserDesc='" + basicUserDesc + '\'' +
                ", rate='" + rate + '\'' +
                ", amount='" + amount + '\'' +
                ", edqty='" + edqty + '\'' +
                ", actualQty='" + actualQty + '\'' +
                ", batchAllocationList=" + batchAllocationList +
                '}';
    }

    public Inventories(int id, String stockItemName, String basicUserDesc, String rate, String amount, String edqty, String actualQty, List<BatchAllocation> batchAllocationList) {
        this.id = id;
        this.stockItemName = stockItemName;
        this.basicUserDesc = basicUserDesc;
        this.rate = rate;
        this.amount = amount;
        this.edqty = edqty;
        this.actualQty = actualQty;
        this.batchAllocationList = batchAllocationList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    protected Inventories(Parcel in) {
        stockItemName = in.readString();
        basicUserDesc = in.readString();
        rate = in.readString();
        amount = in.readString();
        edqty = in.readString();
        actualQty = in.readString();
        batchAllocationList = in.createTypedArrayList(BatchAllocation.CREATOR);
    }

    public static final Creator<Inventories> CREATOR = new Creator<Inventories>() {
        @Override
        public Inventories createFromParcel(Parcel in) {
            return new Inventories(in);
        }

        @Override
        public Inventories[] newArray(int size) {
            return new Inventories[size];
        }
    };

    public String getStockItemName() {
        return stockItemName;
    }

    public void setStockItemName(String stockItemName) {
        this.stockItemName = stockItemName;
    }

    public String getBasicUserDesc() {
        return basicUserDesc;
    }

    public void setBasicUserDesc(String basicUserDesc) {
        this.basicUserDesc = basicUserDesc;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getEdqty() {
        return edqty;
    }

    public void setEdqty(String edqty) {
        this.edqty = edqty;
    }

    public String getActualQty() {
        return actualQty;
    }

    public void setActualQty(String actualQty) {
        this.actualQty = actualQty;
    }

    public List<BatchAllocation> getBatchAllocationList() {
        return batchAllocationList;
    }

    public void setBatchAllocationList(List<BatchAllocation> batchAllocationList) {
        this.batchAllocationList = batchAllocationList;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(stockItemName);
        dest.writeString(basicUserDesc);
        dest.writeString(rate);
        dest.writeString(amount);
        dest.writeString(edqty);
        dest.writeString(actualQty);
        dest.writeTypedList(batchAllocationList);
    }
}
