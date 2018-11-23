package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SalesVoucher  implements Serializable{
    String voucherDate;
    String voucherPartyName;
    String voucherTypeName;

    public SalesVoucher(String voucherDate, String voucherPartyName, String voucherTypeName, String voucherTypeParent, String voucherNumber, String voucherNarration, List<Inventories> voucherInventories, List<InvoiceOrderList> invoiceOrderList, List<LedgerItem> ledger) {
        this.voucherDate = voucherDate;
        this.voucherPartyName = voucherPartyName;
        this.voucherTypeName = voucherTypeName;
        this.voucherTypeParent = voucherTypeParent;
        this.voucherNumber = voucherNumber;
        this.voucherNarration = voucherNarration;
        this.voucherInventories = voucherInventories;
        this.invoiceOrderList = invoiceOrderList;
        this.ledger = ledger;
    }



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

    public List<Inventories> getVoucherInventories() {
        return voucherInventories;
    }

    public void setVoucherInventories(List<Inventories> voucherInventories) {
        this.voucherInventories = voucherInventories;
    }

    public List<InvoiceOrderList> getInvoiceOrderList() {
        return invoiceOrderList;
    }

    public void setInvoiceOrderList(List<InvoiceOrderList> invoiceOrderList) {
        this.invoiceOrderList = invoiceOrderList;
    }

    public List<LedgerItem> getLedger() {
        return ledger;
    }

    public void setLedger(List<LedgerItem> ledger) {
        this.ledger = ledger;
    }
    String voucherTypeParent;
    String voucherNumber;
    String voucherNarration;
    List<Inventories> voucherInventories;
    List<InvoiceOrderList> invoiceOrderList;
    List<LedgerItem> ledger;




}
