package com.aiminfocom.tallyfy.data.BeanModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import android.os.Parcel;
import android.os.Parcelable;

import com.aiminfocom.tallyfy.data.db.RoomDbHelper.DataConverter;

import java.io.Serializable;
import java.util.List;

@Entity(tableName = "StockItem")
public class StockItem implements Parcelable, Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "closingBalance")
    private String closingBalance;
    @ColumnInfo(name = "date")
    private String date;
    @ColumnInfo(name = "hsnCode")
    private String hsnCode;
    @ColumnInfo(name = "minimumAmount")
    private String minimumOrderBase;
    @ColumnInfo(name = "openingBalance")
    private String openingBalance;
    @ColumnInfo(name = "openingRate")
    private String openingRate;
    @ColumnInfo(name = "openingValue")
    private String openingValue;
    @ColumnInfo(name = "parentName")
    private String parentName;
    @ColumnInfo(name = "rate")
    private String rate;
    @ColumnInfo(name = "reorderValue")
    private String reorderValue;
    @ColumnInfo(name = "stockItemName")
    private String stockItemName;
    @ColumnInfo(name = "type")
    private String type;
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "gstRatesLists")
    private List<GstRatesList> gstRatesLists;


    protected StockItem(Parcel in) {
        id =in.readInt();
        closingBalance = in.readString();
        date = in.readString();
        hsnCode = in.readString();
        minimumOrderBase = in.readString();
        openingBalance = in.readString();
        openingRate = in.readString();
        openingValue = in.readString();
        parentName = in.readString();
        rate = in.readString();
        reorderValue = in.readString();
        stockItemName = in.readString();
        type = in.readString();
    }

    @Override
    public String toString() {
        return "StockItem{" +
                "id=" + id +
                ", closingBalance='" + closingBalance + '\'' +
                ", date='" + date + '\'' +
                ", hsnCode='" + hsnCode + '\'' +
                ", minimumOrderBase='" + minimumOrderBase + '\'' +
                ", openingBalance='" + openingBalance + '\'' +
                ", openingRate='" + openingRate + '\'' +
                ", openingValue='" + openingValue + '\'' +
                ", parentName='" + parentName + '\'' +
                ", rate='" + rate + '\'' +
                ", reorderValue='" + reorderValue + '\'' +
                ", stockItemName='" + stockItemName + '\'' +
                ", type='" + type + '\'' +
                ", gstRatesLists=" + gstRatesLists +
                '}';
    }

    public StockItem()
    {

    }
    public static final Creator<StockItem> CREATOR = new Creator<StockItem>() {
        @Override
        public StockItem createFromParcel(Parcel in) {
            return new StockItem(in);
        }

        @Override
        public StockItem[] newArray(int size) {
            return new StockItem[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getClosingBalance() {
        return closingBalance;
    }

    public void setClosingBalance(String closingBalance) {
        this.closingBalance = closingBalance;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHsnCode() {
        return hsnCode;
    }

    public void setHsnCode(String hsnCode) {
        this.hsnCode = hsnCode;
    }

    public String getMinimumOrderBase() {
        return minimumOrderBase;
    }

    public void setMinimumOrderBase(String minimumOrderBase) {
        this.minimumOrderBase = minimumOrderBase;
    }

    public String getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(String openingBalance) {
        this.openingBalance = openingBalance;
    }

    public String getOpeningRate() {
        return openingRate;
    }

    public void setOpeningRate(String openingRate) {
        this.openingRate = openingRate;
    }

    public String getOpeningValue() {
        return openingValue;
    }

    public void setOpeningValue(String openingValue) {
        this.openingValue = openingValue;
    }

    public String getParentName() {
        return parentName;
    }

    public void setParentName(String parentName) {
        this.parentName = parentName;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReorderValue() {
        return reorderValue;
    }

    public void setReorderValue(String reorderValue) {
        this.reorderValue = reorderValue;
    }

    public String getStockItemName() {
        return stockItemName;
    }

    public void setStockItemName(String stockItemName) {
        this.stockItemName = stockItemName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<GstRatesList> getGstRatesLists() {
        return gstRatesLists;
    }

    public void setGstRatesLists(List<GstRatesList> gstRatesLists) {
        this.gstRatesLists = gstRatesLists;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(closingBalance);
        dest.writeString(date);
        dest.writeString(hsnCode);
        dest.writeString(minimumOrderBase);
        dest.writeString(openingBalance);
        dest.writeString(openingRate);
        dest.writeString(openingValue);
        dest.writeString(parentName);
        dest.writeString(rate);
        dest.writeString(reorderValue);
        dest.writeString(stockItemName);
        dest.writeString(type);
    }
}
