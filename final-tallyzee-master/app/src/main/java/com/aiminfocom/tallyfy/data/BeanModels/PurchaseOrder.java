package com.aiminfocom.tallyfy.data.BeanModels;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by GulshanPC on 19/07/2018.
 */
@Entity(tableName = "PurchaseOrder")
public class PurchaseOrder {
    @PrimaryKey(autoGenerate = true)
    @SerializedName("Id")
    @ColumnInfo(name = "Id")
    public int id;

    @SerializedName("dorDate")
    @ColumnInfo(name = "dorDate")
    public String dorDate;
    @SerializedName("dorName")
    @ColumnInfo(name = "dorName")
    public String dorName;
    @SerializedName("dorItem")
    @ColumnInfo(name = "dorItem")
    public String dorItem;
    @SerializedName("dorPndgqty")
    @ColumnInfo(name = "dorPndgqty")
    public String dorPndgqty;
    @SerializedName("dorRate")
    @ColumnInfo(name = "dorRate")
    public String dorRate;
    @SerializedName("orderDicount")
    @ColumnInfo(name = "orderDicount")
    public String orderDicount;

   public  PurchaseOrder(String dorDate,String dorName,String dorValue,String dorPndgqty,String dorRate,String dorDueon,String orderDicount,String orderDueDays,String dorItem)
    {
        this.dorDate=dorDate;
        this.dorDueon=dorDueon;
        this.dorName=dorName;
        this.dorItem=dorItem;
        this.dorPndgqty=dorPndgqty;
        this.dorValue=dorValue;
        this.dorRate=dorRate;
        this.orderDicount=orderDicount;
        this.orderDueDays=orderDueDays;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDorDate() {
        return dorDate;
    }

    public void setDorDate(String dorDate) {
        this.dorDate = dorDate;
    }

    public String getDorName() {
        return dorName;
    }

    public void setDorName(String dorName) {
        this.dorName = dorName;
    }

    public String getDorItem() {
        return dorItem;
    }

    public void setDorItem(String dorItem) {
        this.dorItem = dorItem;
    }

    public String getDorPndgqty() {
        return dorPndgqty;
    }

    public void setDorPndgqty(String dorPndgqty) {
        this.dorPndgqty = dorPndgqty;
    }

    public String getDorRate() {
        return dorRate;
    }

    public void setDorRate(String dorRate) {
        this.dorRate = dorRate;
    }

    public String getOrderDicount() {
        return orderDicount;
    }

    public void setOrderDicount(String orderDicount) {
        this.orderDicount = orderDicount;
    }

    public String getDorValue() {
        return dorValue;
    }

    public void setDorValue(String dorValue) {
        this.dorValue = dorValue;
    }

    public String getDorDueon() {
        return dorDueon;
    }

    public void setDorDueon(String dorDueon) {
        this.dorDueon = dorDueon;
    }

    public String getOrderDueDays() {
        return orderDueDays;
    }

    public void setOrderDueDays(String orderDueDays) {
        this.orderDueDays = orderDueDays;
    }

    @SerializedName("dorValue")
    @ColumnInfo(name = "dorValue")
    public String dorValue;
    @SerializedName("dorDueon")
    @ColumnInfo(name = "dorDueon")
    public String dorDueon;
    @SerializedName("orderDueDays")
    @ColumnInfo(name = "orderDueDays")
    public String orderDueDays;
}
