package com.aiminfocom.tallyfy.data.Models;

import java.io.Serializable;

public class CreditNoteItem implements Serializable {
    String billName;
    String billAmount;
    String billType;

    public String getBillName() {
        return billName;
    }

    public void setBillName(String billName) {
        this.billName = billName;
    }

    public String getBillAmount() {
        return billAmount;
    }

    public void setBillAmount(String billAmount) {
        this.billAmount = billAmount;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public CreditNoteItem(String billName, String billAmount, String billType) {
        this.billName = billName;
        this.billAmount = billAmount;
        this.billType = billType;
    }
}
