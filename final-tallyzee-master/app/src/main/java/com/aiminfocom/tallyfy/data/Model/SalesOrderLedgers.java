package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.aiminfocom.tallyfy.data.db.RoomDbHelper.DataConverter;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "SalesOrderLedgers")
public class SalesOrderLedgers implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "ledgerAmount")
    private String ledgerAmount;
    @ColumnInfo(name = "ledgerName")
    private  String ledgerName;
    @TypeConverters(DataConverter.class)
    @ColumnInfo(name = "salesOrderItemList")
    private  List<SalesOrderItem> salesOrderItemList;

    public String getLedgerAmount() {
        return ledgerAmount;
    }

    public void setLedgerAmount(String ledgerAmount) {
        this.ledgerAmount = ledgerAmount;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public List<SalesOrderItem> getSalesOrderItemList() {
        return salesOrderItemList;
    }

    public void setSalesOrderItemList(List<SalesOrderItem> salesOrderItemList) {
        this.salesOrderItemList = salesOrderItemList;
    }

    public SalesOrderLedgers()
    {

    }

    @Override
    public String toString() {
        return "SalesOrderLedgers{" +
                "id=" + id +
                ", ledgerAmount='" + ledgerAmount + '\'' +
                ", ledgerName='" + ledgerName + '\'' +
                ", salesOrderItemList=" + salesOrderItemList +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public SalesOrderLedgers(String ledgerAmount, String ledgerName, List<SalesOrderItem> salesOrderItemList) {
        this.ledgerAmount = ledgerAmount;
        this.ledgerName = ledgerName;
        this.salesOrderItemList = salesOrderItemList;
    }
}
