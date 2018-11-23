package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "SalesOrderBatchAlloction")
public class SalesOrderBatchAlloction implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "gowdownName")
    private String gowdownName;
    @ColumnInfo(name = "orderNumber")
    private  String orderNumber;
    @ColumnInfo(name = "batchName")
    private  String batchName;
    @ColumnInfo(name = "amount")
    private  String amount;
    @ColumnInfo(name = "actualQty")
    private  String actualQty;
    @ColumnInfo(name = "billQty")
    private  String billQty;
    @ColumnInfo(name = "orderDueDate")
    private   String orderDueDate;

    public SalesOrderBatchAlloction()
    {

    }

    @Override
    public String toString() {
        return "SalesOrderBatchAlloction{" +
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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SalesOrderBatchAlloction(String gowdownName, String orderNumber, String batchName, String amount, String actualQty, String billQty, String orderDueDate) {
        this.gowdownName = gowdownName;
        this.orderNumber = orderNumber;
        this.batchName = batchName;
        this.amount = amount;
        this.actualQty = actualQty;
        this.billQty = billQty;
        this.orderDueDate = orderDueDate;
    }
}
