package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "PurchaseOrderItem")
public class PurchaseOrderItem implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "billName")
    private String billName;
    @ColumnInfo(name = "billAmount")
    private  String billAmount;
    @ColumnInfo(name = "billType")
    private  String billType;
    @ColumnInfo(name = "orderDueDate")
    private  String orderDueDate;

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getOrderDueDate() {
        return orderDueDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setOrderDueDate(String orderDueDate) {
        this.orderDueDate = orderDueDate;
    }

    public PurchaseOrderItem(String billName, String billAmount, String billType, String orderDueDate) {
        this.billName = billName;
        this.billAmount = billAmount;
        this.billType = billType;
        this.orderDueDate = orderDueDate;
    }


}
