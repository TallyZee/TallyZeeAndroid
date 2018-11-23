package com.aiminfocom.tallyfy.data.Models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity(tableName = "SalesOrderVoucher")
public class SalesOrderVoucher implements Serializable{
    @ColumnInfo(name = "voucherDate")
    private String voucherDate;
    @ColumnInfo(name = "voucherPartyName")
    private  String voucherPartyName;
    @ColumnInfo(name = "voucherTypeName")
    private  String voucherTypeName;
    @ColumnInfo(name ="voucherTypeParent")
    private  String voucherTypeParent;
    @ColumnInfo(name = "voucherNumber")
    private  String voucherNumber;
    @ColumnInfo(name = "voucherNarration")
    private   String voucherNarration;
    @ColumnInfo(name = "voucherInventories")
    private   List<SalesOrderInventorieList> voucherInventories;
    @ColumnInfo(name = "ledger")
    private  List<SalesOrderLedgers> ledger;

    public String getVoucherDate() {
        return voucherDate;
    }

    public void setVoucherDate(String voucherDate) {
        this.voucherDate = voucherDate;
    }

    public String getVoucherPartyName() {
        return voucherPartyName;
    }

    public void setVoucherPartyName(String voucherPartyName) {
        this.voucherPartyName = voucherPartyName;
    }

    public String getVoucherTypeName() {
        return voucherTypeName;
    }

    public void setVoucherTypeName(String voucherTypeName) {
        this.voucherTypeName = voucherTypeName;
    }

    public String getVoucherTypeParent() {
        return voucherTypeParent;
    }

    public void setVoucherTypeParent(String voucherTypeParent) {
        this.voucherTypeParent = voucherTypeParent;
    }

    public String getVoucherNumber() {
        return voucherNumber;
    }

    public void setVoucherNumber(String voucherNumber) {
        this.voucherNumber = voucherNumber;
    }

    public String getVoucherNarration() {
        return voucherNarration;
    }

    public void setVoucherNarration(String voucherNarration) {
        this.voucherNarration = voucherNarration;
    }

    public List<SalesOrderInventorieList> getVoucherInventories() {
        return voucherInventories;
    }

    public void setVoucherInventories(List<SalesOrderInventorieList> voucherInventories) {
        this.voucherInventories = voucherInventories;
    }

    public List<SalesOrderLedgers> getLedger() {
        return ledger;
    }

    public void setLedger(List<SalesOrderLedgers> ledger) {
        this.ledger = ledger;
    }

    public SalesOrderVoucher(String voucherDate, String voucherPartyName, String voucherTypeName, String voucherTypeParent, String voucherNumber, String voucherNarration, List<SalesOrderInventorieList> voucherInventories, List<SalesOrderLedgers> ledger) {
        this.voucherDate = voucherDate;
        this.voucherPartyName = voucherPartyName;
        this.voucherTypeName = voucherTypeName;
        this.voucherTypeParent = voucherTypeParent;
        this.voucherNumber = voucherNumber;
        this.voucherNarration = voucherNarration;
        this.voucherInventories = voucherInventories;
        this.ledger = ledger;
    }



}
