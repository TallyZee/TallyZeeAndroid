package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;
import java.util.List;

public class PurchaseOrderVoucher implements Serializable{
    String voucherDate;
    String voucherPartyName;
    String voucherTypeName;
    String voucherTypeParent;
    String voucherNumber;
    String voucherNarration;
    List<PurchaseOrderInventoriesList> purchaseOrderInventoriesLists;

    public List<PurchaseOrderInventoriesList> getPurchaseOrderInventoriesLists() {
        return purchaseOrderInventoriesLists;
    }

    public void setPurchaseOrderInventoriesLists(List<PurchaseOrderInventoriesList> purchaseOrderInventoriesLists) {
        this.purchaseOrderInventoriesLists = purchaseOrderInventoriesLists;
    }

    public List<PurchaseOrderLedger> getLedger() {
        return ledger;
    }

    public void setLedger(List<PurchaseOrderLedger> ledger) {
        this.ledger = ledger;
    }

    public PurchaseOrderVoucher(String voucherDate, String voucherPartyName, String voucherTypeName, String voucherTypeParent, String voucherNumber, String voucherNarration, List<PurchaseOrderInventoriesList> purchaseOrderInventoriesLists, List<PurchaseOrderLedger> ledger) {
        this.voucherDate = voucherDate;
        this.voucherPartyName = voucherPartyName;
        this.voucherTypeName = voucherTypeName;
        this.voucherTypeParent = voucherTypeParent;
        this.voucherNumber = voucherNumber;
        this.voucherNarration = voucherNarration;
        this.purchaseOrderInventoriesLists = purchaseOrderInventoriesLists;
        this.ledger = ledger;
    }

    List<PurchaseOrderLedger> ledger;
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
}
