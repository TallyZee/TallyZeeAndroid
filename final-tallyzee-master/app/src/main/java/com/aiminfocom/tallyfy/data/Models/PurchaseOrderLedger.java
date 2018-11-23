package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;
import java.util.List;

public class PurchaseOrderLedger implements Serializable {
    String ledgerAmount;
    String ledgerName;
    List<PurchaseOrderItem> purchaseOrderItemList;

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

    public List<PurchaseOrderItem> getPurchaseOrderItemList() {
        return purchaseOrderItemList;
    }

    public void setPurchaseOrderItemList(List<PurchaseOrderItem> purchaseOrderItemList) {
        this.purchaseOrderItemList = purchaseOrderItemList;
    }

    public PurchaseOrderLedger(String ledgerAmount, String ledgerName, List<PurchaseOrderItem> purchaseOrderItemList) {
        this.ledgerAmount = ledgerAmount;
        this.ledgerName = ledgerName;
        this.purchaseOrderItemList = purchaseOrderItemList;
    }
}
