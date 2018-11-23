package com.aiminfocom.tallyfy.data.BeanModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GulshanPC on 18/07/2018.
 */
@Entity(tableName = "Stock")
public class Stock {
    @SerializedName("ID")
    @ColumnInfo(name = "ID")
    @PrimaryKey(autoGenerate = true)
    public int id;
    @SerializedName("stockName")
    @ColumnInfo(name ="stockName")
    public String stockName;
    @SerializedName("stockAmount")
    @ColumnInfo(name = "stockAmount")
    public String stockAmount;
    @SerializedName("stockQuantity")
    @ColumnInfo(name = "stockQuantity")
    public String stockQuantity;
    @SerializedName("stockRate")
    @ColumnInfo(name = "stockRate")
    public String stockRate;

    public Stock(String stockName,String stockAmount,String stockQuantity,String stockRate)
    {
        this.stockName=stockName;
        this.stockAmount=stockAmount;
        this.stockQuantity=stockQuantity;
        this.stockRate=stockRate;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public String getStockAmount() {
        return stockAmount;
    }

    public void setStockAmount(String stockAmount) {
        this.stockAmount = stockAmount;
    }

    public String getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(String stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getStockRate() {
        return stockRate;
    }

    public void setStockRate(String stockRate) {
        this.stockRate = stockRate;
    }



}
