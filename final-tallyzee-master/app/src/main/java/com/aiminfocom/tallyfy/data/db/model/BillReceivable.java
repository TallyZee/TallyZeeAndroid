package com.aiminfocom.tallyfy.data.db.model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "BillsReceivable")
public class BillReceivable {
    @PrimaryKey(autoGenerate = true)
    private int Id;
    @ColumnInfo(name = "billDate")
    private  String billDate;
    @ColumnInfo(name = "billRef")
    private String billRef;
    @ColumnInfo(name = "billParty")
    private String billParty;
    @ColumnInfo(name = "billCl")
    private String billCl;
    @ColumnInfo(name = "billDue")
    private String billDue;
    @ColumnInfo(name = "billOverDue")
    private String billOverDue;

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getBillDate() {
        return billDate;
    }

    public void setBillDate(String billDate) {
        this.billDate = billDate;
    }

    public String getBillRef() {
        return billRef;
    }

    public void setBillRef(String billRef) {
        this.billRef = billRef;
    }

    public String getBillParty() {
        return billParty;
    }

    public void setBillParty(String billParty) {
        this.billParty = billParty;
    }

    public String getBillCl() {
        return billCl;
    }

    public void setBillCl(String billCl) {
        this.billCl = billCl;
    }

    public String getBillDue() {
        return billDue;
    }

    public void setBillDue(String billDue) {
        this.billDue = billDue;
    }

    public String getBillOverDue() {
        return billOverDue;
    }

    public void setBillOverDue(String billOverDue) {
        this.billOverDue = billOverDue;
    }
}
