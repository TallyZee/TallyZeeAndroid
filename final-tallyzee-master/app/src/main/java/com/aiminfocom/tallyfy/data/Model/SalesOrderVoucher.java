package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.aiminfocom.tallyfy.data.db.RoomDbHelper.DataConverter;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
@Entity(tableName = "SalesOrderVoucher")
public class SalesOrderVoucher implements Serializable{
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "voucherDate")
    private String voucherDate;

    @ColumnInfo(name = "voucherPartyName")
    private String voucherPartyName;
    @ColumnInfo(name = "voucherTypeName")
    private String voucherTypeName;
    @ColumnInfo(name = "voucherTypeParent")
    private String voucherTypeParent;
    @ColumnInfo(name = "voucherNumber")
    private String voucherNumber;
    @ColumnInfo(name = "voucherNarration")
    private String voucherNarration;
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "voucherInventories")
    private  List<SalesOrderInventorieList> voucherInventories;
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "ledger")
    private List<SalesOrderLedgers> ledger;



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

    @Override
    public String toString() {
        return "SalesOrderVoucher{" +
                "id=" + id +
                ", voucherDate='" + voucherDate + '\'' +
                ", voucherPartyName='" + voucherPartyName + '\'' +
                ", voucherTypeName='" + voucherTypeName + '\'' +
                ", voucherTypeParent='" + voucherTypeParent + '\'' +
                ", voucherNumber='" + voucherNumber + '\'' +
                ", voucherNarration='" + voucherNarration + '\'' +
                ", voucherInventories=" + voucherInventories +
                ", ledger=" + ledger +
                '}';
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

    public SalesOrderVoucher()
    {

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
;


}
