package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
@Entity(tableName = "SalesOrderItem")
public class SalesOrderItem implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "billref")
    private String billref;
    @ColumnInfo(name = "billAmount")
    private String billAmount;
    @ColumnInfo(name = "billName")
    private  String billName;
    @ColumnInfo(name = "billCreditPeriod")
    private  String billCreditPeriod;

    public String getBillref() {
        return billref;
    }

    public void setBillref(String billref) {
        this.billref = billref;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "SalesOrderItem{" +
                "id=" + id +
                ", billref='" + billref + '\'' +
                ", billAmount='" + billAmount + '\'' +
                ", billName='" + billName + '\'' +
                ", billCreditPeriod='" + billCreditPeriod + '\'' +
                '}';
    }

    public String getBillCreditPeriod() {
        return billCreditPeriod;
    }

    public void setBillCreditPeriod(String billCreditPeriod) {
        this.billCreditPeriod = billCreditPeriod;
    }

    public SalesOrderItem(String billref, String billAmount, String billName, String billCreditPeriod) {
        this.billref = billref;
        this.billAmount = billAmount;
        this.billName = billName;
        this.billCreditPeriod = billCreditPeriod;
    }
}
