package com.aiminfocom.tallyfy.data.Model;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.aiminfocom.tallyfy.data.db.RoomDbHelper.DataConverter;

import java.io.Serializable;
import java.util.List;
@Entity(tableName = "PurchaseOrderLedger")
public class PurchaseOrderLedger implements Serializable {
    @PrimaryKey(autoGenerate = true)
    private int id;
    @ColumnInfo(name = "ledgerAmount")
   private String amount;
    @ColumnInfo(name = "ledgerName")
    private String ledgerName;
    @ColumnInfo(name = "purchaseOrderItemList")
    @TypeConverters(DataConverter.class)
    private  List<PurchaseOrderItem> purchaseOrderItemList;



    public PurchaseOrderLedger()
    {

    }

    @Override
    public String toString() {
        return "PurchaseOrderLedger{" +
                "id=" + id +
                ", ledgerAmount='" + amount + '\'' +
                ", ledgerName='" + ledgerName + '\'' +
                ", purchaseOrderItemList=" + purchaseOrderItemList +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLedgerName() {
        return ledgerName;
    }

    public void setLedgerName(String ledgerName) {
        this.ledgerName = ledgerName;
    }

    public List<PurchaseOrderItem> getPurchaseOrderItemList() {
        return purchaseOrderItemList;
    }

    public void setPurchaseOrderItemList(List<PurchaseOrderItem> purchaseOrderItemList) {
        this.purchaseOrderItemList = purchaseOrderItemList;
    }

    public PurchaseOrderLedger(String ledgerAmount, String ledgerName, List<PurchaseOrderItem> purchaseOrderItemList) {
        this.amount = ledgerAmount;
        this.ledgerName = ledgerName;
        this.purchaseOrderItemList = purchaseOrderItemList;
    }
}
