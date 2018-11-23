package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;
import java.util.List;

public class SalesOrderLedgers implements Serializable {
    String ledgerAmount;
    String ledgerName;
    List<SalesOrderItem> salesOrderItemList;

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

    public SalesOrderLedgers(String ledgerAmount, String ledgerName, List<SalesOrderItem> salesOrderItemList) {
        this.ledgerAmount = ledgerAmount;
        this.ledgerName = ledgerName;
        this.salesOrderItemList = salesOrderItemList;
    }
}
