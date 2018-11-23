package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.aiminfocom.tallyfy.data.db.RoomDbHelper.DataConverter;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "SalesOrderInventorieList")
public class SalesOrderInventorieList implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "stockItemName")
    private String stockItemName;
    @ColumnInfo(name = "basicUserDesc")
    private  String basicUserDesc;
    @ColumnInfo(name = "rate")
    private String rate;
    @ColumnInfo(name = "amount")
    private String amount;
    @ColumnInfo(name = "edqty")
    private  String edqty;
    @ColumnInfo(name = "actualQty")
    private  String actualQty;
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "batchAllocationList")
    private  List<SalesOrderBatchAlloction> batchAllocationList;


    public SalesOrderInventorieList()
    {

    }

    @Override
    public String toString() {
        return "SalesOrderInventorieList{" +
                "id=" + id +
                ", stockItemName='" + stockItemName + '\'' +
                ", basicUserDesc='" + basicUserDesc + '\'' +
                ", rate='" + rate + '\'' +
                ", amount='" + amount + '\'' +
                ", edqty='" + edqty + '\'' +
                ", actualQty='" + actualQty + '\'' +
                ", batchAllocationList=" + batchAllocationList +
                '}';
    }

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

    public List<SalesOrderBatchAlloction> getBatchAllocationList() {
        return batchAllocationList;
    }

    public void setBatchAllocationList(List<SalesOrderBatchAlloction> batchAllocationList) {
        this.batchAllocationList = batchAllocationList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SalesOrderInventorieList(String stockItemName, String basicUserDesc, String rate, String amount, String edqty, String actualQty, List<SalesOrderBatchAlloction> batchAllocationList) {
        this.stockItemName = stockItemName;
        this.basicUserDesc = basicUserDesc;
        this.rate = rate;
        this.amount = amount;
        this.edqty = edqty;
        this.actualQty = actualQty;
        this.batchAllocationList = batchAllocationList;
    }

}
